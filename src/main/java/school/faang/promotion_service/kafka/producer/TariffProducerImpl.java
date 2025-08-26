package school.faang.promotion_service.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import school.faang.avro.tariff.CreateTariffEvent;
import school.faang.avro.tariff.UpdateTariffEvent;

@RequiredArgsConstructor
@Component
public class TariffProducerImpl implements TariffProducer {
    @Value("${spring.kafka.topics.promotion-tariffs-events.name}")
    private String promotionTariffsEventsTopic;

    private final KafkaTemplate<String, CreateTariffEvent> createTariffProducer;
    private final KafkaTemplate<String, UpdateTariffEvent> updateTariffProducer;

    @Override
    public void onCreate(long id, CreateTariffEvent event) {
        createTariffProducer.send(
                promotionTariffsEventsTopic,
                String.valueOf(id),
                event
        );
    }

    @Override
    public void onUpdate(UpdateTariffEvent event) {
        updateTariffProducer.send(
                promotionTariffsEventsTopic,
                String.valueOf(event.getId()),
                event
        );
    }
}
