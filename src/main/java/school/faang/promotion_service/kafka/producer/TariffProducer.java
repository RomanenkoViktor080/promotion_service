package school.faang.promotion_service.kafka.producer;

import school.faang.avro.tariff.CreateTariffEvent;
import school.faang.avro.tariff.UpdateTariffEvent;

public interface TariffProducer {
    void onCreate(long id, CreateTariffEvent event);

    void onUpdate(UpdateTariffEvent event);
}
