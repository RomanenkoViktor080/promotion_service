package school.faang.promotion_service.kafka.producer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import school.faang.promotion_service.kafka.EnvelopeMessage;
import school.faang.promotion_service.kafka.dto.PromotionPurchaseCommand;
import school.faang.promotion_service.kafka.dto.UserChangeTariffEvent;

@RequiredArgsConstructor
@Component
public class PromotionProducerImpl implements PromotionProducer {
    private final KafkaTemplate<String, Object> producer;
    private final ObjectMapper objectMapper;


    public void sendPurchaseRequest(PromotionPurchaseCommand dto) {
        JsonNode payloadNode = objectMapper.valueToTree(dto);
        producer.send(
                "account.transaction.commands",
                String.valueOf(dto.getId()),
                new EnvelopeMessage(dto.getType(), payloadNode)
        );
    }

    public void sendUserChangeTariff(UserChangeTariffEvent dto) {
        JsonNode payloadNode = objectMapper.valueToTree(dto);
        producer.send(
                "promotion.user.events",
                String.valueOf(dto.getId()),
                new EnvelopeMessage(dto.getType(), payloadNode)
        );
    }
}
