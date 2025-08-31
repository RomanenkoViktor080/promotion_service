package school.faang.promotion_service.service;

import school.faang.avro.user.UserViewEvent;

public interface PromotionCounterService {
    void onView(UserViewEvent dto);

    void initCounter(long userId, long promotionId);
}
