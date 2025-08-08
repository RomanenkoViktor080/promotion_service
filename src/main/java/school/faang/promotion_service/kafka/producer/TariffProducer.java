package school.faang.promotion_service.kafka.producer;

import school.faang.promotion_service.kafka.dto.tariff.CreateTariffEvent;
import school.faang.promotion_service.kafka.dto.tariff.UpdateTariffEvent;

public interface TariffProducer {
    void onCreate(long id, CreateTariffEvent event);

    void onUpdate(UpdateTariffEvent event);
}
