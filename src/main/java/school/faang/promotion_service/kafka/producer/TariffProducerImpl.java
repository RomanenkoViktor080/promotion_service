package school.faang.promotion_service.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import school.faang.promotion_service.kafka.EnvelopeMessage;
import school.faang.promotion_service.kafka.dto.tariff.CreateTariffEvent;
import school.faang.promotion_service.kafka.dto.tariff.UpdateTariffEvent;

@RequiredArgsConstructor
@Component
public class TariffProducerImpl implements TariffProducer {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, Object> producer;

    @Override
    public void onCreate(long id, CreateTariffEvent event) {
        producer.send(
                "promotion.tariff.events",
                String.valueOf(id),
                new EnvelopeMessage(event.getType(), objectMapper.valueToTree(event))
        );
    }

    @Override
    public void onUpdate(UpdateTariffEvent event) {
        producer.send(
                "promotion.tariff.events",
                String.valueOf(event.getId()),
                new EnvelopeMessage(event.getType(), objectMapper.valueToTree(event))
        );
    }
}
