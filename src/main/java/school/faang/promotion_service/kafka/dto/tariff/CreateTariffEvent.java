package school.faang.promotion_service.kafka.dto.tariff;

import school.faang.promotion_service.kafka.EnvelopeMessageJsonNode;

public record CreateTariffEvent(
        long id,
        double boostFactor,
        boolean active
) implements EnvelopeMessageJsonNode {
    @Override
    public String getType() {
        return "TARIFF_CREATE";
    }

    @Override
    public long getId() {
        return id;
    }
}
