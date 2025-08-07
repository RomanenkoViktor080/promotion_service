package school.faang.promotion_service.kafka.producer;

import school.faang.promotion_service.kafka.dto.PromotionPurchaseCommand;
import school.faang.promotion_service.kafka.dto.UserChangeTariffEvent;

public interface PromotionProducer {
    void sendPurchaseRequest(PromotionPurchaseCommand dto);

    void sendUserChangeTariff(UserChangeTariffEvent dto);
}
