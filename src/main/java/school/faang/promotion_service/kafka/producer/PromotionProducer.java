package school.faang.promotion_service.kafka.producer;

import school.faang.avro.promotion.PromotionPurchaseCommand;
import school.faang.avro.user.UserChangeTariffEvent;

public interface PromotionProducer {
    void sendPurchaseRequest(PromotionPurchaseCommand dto);

    void sendUserChangeTariff(UserChangeTariffEvent dto);
}
