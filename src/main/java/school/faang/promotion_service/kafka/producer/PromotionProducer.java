package school.faang.promotion_service.kafka.producer;

import school.faang.promotion_service.kafka.dto.promotion.PromotionPurchaseCommand;
import school.faang.promotion_service.kafka.dto.user.UserChangeTariffEvent;

public interface PromotionProducer {
    void sendPurchaseRequest(PromotionPurchaseCommand dto);

    void sendUserChangeTariff(UserChangeTariffEvent dto);
}
