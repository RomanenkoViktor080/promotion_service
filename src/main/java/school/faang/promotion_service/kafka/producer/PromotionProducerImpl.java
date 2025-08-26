package school.faang.promotion_service.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import school.faang.avro.promotion.PromotionPurchaseCommand;
import school.faang.avro.user.UserChangeTariffEvent;

@RequiredArgsConstructor
@Component
public class PromotionProducerImpl implements PromotionProducer {
    @Value("${spring.kafka.topics.account-transactions-commands.name}")
    private String accountTransactionsCommandsTopic;
    @Value("${spring.kafka.topics.promotion-users-events.name}")
    private String promotionUsersEventsTopic;

    private final KafkaTemplate<String, PromotionPurchaseCommand> purchaseProducer;
    private final KafkaTemplate<String, UserChangeTariffEvent> changeTariffProducer;

    public void sendPurchaseRequest(PromotionPurchaseCommand dto) {
        purchaseProducer.send(
                accountTransactionsCommandsTopic,
                String.valueOf(dto.getUserId()),
                dto
        );
    }

    public void sendUserChangeTariff(UserChangeTariffEvent dto) {
        changeTariffProducer.send(
                promotionUsersEventsTopic,
                String.valueOf(dto.getUserId()),
                dto
        );
    }
}
