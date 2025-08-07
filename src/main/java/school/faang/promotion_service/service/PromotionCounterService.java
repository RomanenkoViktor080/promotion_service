package school.faang.promotion_service.service;

import school.faang.promotion_service.kafka.dto.UserViewDto;

public interface PromotionCounterService {
    void onView(UserViewDto dto);

    void initCounter(long userId, long promotionId);
}
