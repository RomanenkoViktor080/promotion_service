package school.faang.promotion_service.kafka.dto.user;

import lombok.Builder;
import school.faang.promotion_service.kafka.EnvelopeMessageJsonNode;

@Builder
public record UserChangeTariffEvent(
        long userId,
        Long tariffId,
        Long promotionId
) implements EnvelopeMessageJsonNode {
    @Override
    public String getType() {
        return "USER_CHANGE_TARIFF";
    }

    @Override
    public long getId() {
        return userId();
    }
}
