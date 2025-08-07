package school.faang.promotion_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import school.faang.promotion_service.entity.user_promotion.UserPromotion;
import school.faang.promotion_service.entity.user_promotion.UserPromotionStatus;
import school.faang.promotion_service.kafka.dto.user.UserChangeTariffEvent;
import school.faang.promotion_service.kafka.dto.user.UserViewEvent;
import school.faang.promotion_service.kafka.producer.PromotionProducer;
import school.faang.promotion_service.repository.UserPromotionRepository;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class PromotionCounterServiceImpl implements PromotionCounterService {
    private static final String VIEWS_KEY = "views";
    private static final String LIMIT_KEY = "limit";
    private static final String INIT_VIEWS = "0";
    private static final int DEFAULT_TTL = 30;

    private final UserPromotionRepository promotionRepository;
    private final StringRedisTemplate redisTemplate;
    private final PromotionProducer promotionProducer;

    @Override
    @Transactional
    public void onView(UserViewEvent dto) {
        String redisKey = getKey(dto.userId(), dto.promotionId());
        if (!redisTemplate.hasKey(redisKey)) {
            initCounter(dto.userId(), dto.promotionId());
        }
        long views = redisTemplate.opsForHash().increment(redisKey, VIEWS_KEY, 1L);
        Object currentLimit = redisTemplate.opsForHash().get(redisKey, LIMIT_KEY);

        if (currentLimit != null && views >= Long.parseLong(currentLimit.toString())) {
            deactivatePromotion(dto.promotionId(), dto.userId(), views);
        }
    }

    public void initCounter(long userId, long promotionId) {
        UserPromotion promotion = promotionRepository.getByIdOrThrow(promotionId);

        String key = getKey(userId, promotionId);
        redisTemplate.opsForHash().put(key, VIEWS_KEY, INIT_VIEWS);
        redisTemplate.opsForHash().put(key, LIMIT_KEY, String.valueOf(promotion.getPromotionCount()));
        redisTemplate.expire(key, Duration.ofDays(DEFAULT_TTL));
    }

    private String getKey(long userId, long promotionId) {
        return "user:" + userId + ":promotion:" + promotionId;
    }

    private void deactivatePromotion(long promotionId, long userId, long views) {
        promotionRepository.updateStatusAndUsedCount(promotionId, UserPromotionStatus.UNACTIVE, views);
        promotionProducer.sendUserChangeTariff(
                UserChangeTariffEvent.builder()
                        .userId(userId)
                        .tariffId(null)
                        .promotionId(null)
                        .build()
        );
    }
}
