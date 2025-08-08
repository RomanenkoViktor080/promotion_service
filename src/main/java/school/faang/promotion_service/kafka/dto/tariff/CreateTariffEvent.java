package school.faang.promotion_service.kafka.dto.tariff;

public record CreateTariffEvent(
        double boostFactor,
        boolean active
) {
    public String getType() {
        return "TARIFF_CREATE";
    }
}
