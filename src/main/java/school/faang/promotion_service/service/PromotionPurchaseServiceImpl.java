package school.faang.promotion_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.avro.promotion.PromotionPurchaseCommand;
import school.faang.avro.promotion.PromotionPurchaseCompleteEvent;
import school.faang.avro.user.UserChangeTariffEvent;
import school.faang.promotion_service.client.AccountServiceClient;
import school.faang.promotion_service.config.context.UserContext;
import school.faang.promotion_service.dto.promotion.PromotionPurchaseDto;
import school.faang.promotion_service.entity.Tariff;
import school.faang.promotion_service.entity.user_promotion.UserPromotion;
import school.faang.promotion_service.entity.user_promotion.UserPromotionStatus;
import school.faang.promotion_service.kafka.producer.PromotionProducer;
import school.faang.promotion_service.policy.PromotionPurchasePolicy;
import school.faang.promotion_service.repository.TariffRepository;
import school.faang.promotion_service.repository.UserPromotionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PromotionPurchaseServiceImpl implements PromotionPurchaseService {
    private final PromotionPurchasePolicy promotionPurchasePolicy;
    private final AccountServiceClient accountServiceClient;
    private final TariffRepository tariffRepository;
    private final UserPromotionRepository userPromotionRepository;
    private final PromotionProducer producer;
    private final PromotionCounterService promotionCounterService;
    private final UserContext userContext;

    @Override
    public void purchase(PromotionPurchaseDto dto) {
        long userId = userContext.getUserId();
        Tariff tariff = tariffRepository.getByIdOrThrow(dto.tariffId());
        Long userBalance = accountServiceClient.getAccountBalance(userId);
        promotionPurchasePolicy.validate(BigDecimal.valueOf(userBalance), tariff.getBasePrice());

        producer.sendPurchaseRequest(
                PromotionPurchaseCommand.newBuilder()
                        .setUserId(userId)
                        .setTariffId(tariff.getId())
                        .setPaymentMethodId(dto.paymentMethodId())
                        .setPrice(tariff.getBasePrice())
                        .build()
        );
    }

    @Override
    public void promote(PromotionPurchaseCompleteEvent dto) {
        Tariff tariff = tariffRepository.getByIdOrThrow(dto.getTariffId());
        UserPromotion purchase = UserPromotion.builder()
                .status(UserPromotionStatus.ACTIVE)
                .userId(dto.getUserId())
                .tariff(tariff)
                .promotionCount(dto.getPromotionCount())
                .basePrice(dto.getPrice())
                .startedAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(tariff.getDurationDays()))
                .build();

        userPromotionRepository.save(purchase);

        promotionCounterService.initCounter(dto.getUserId(), purchase.getId());

        producer.sendUserChangeTariff(
                UserChangeTariffEvent.newBuilder()
                        .setUserId(dto.getUserId())
                        .setTariffId(dto.getTariffId())
                        .setPromotionId(purchase.getId())
                        .build()
        );
    }
}
