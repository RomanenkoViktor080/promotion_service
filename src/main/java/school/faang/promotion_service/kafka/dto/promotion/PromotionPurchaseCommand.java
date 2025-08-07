package school.faang.promotion_service.kafka.dto.promotion;

import school.faang.promotion_service.kafka.EnvelopeMessageJsonNode;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PromotionPurchaseCommand(
        long userId,
        long tariffId,
        long paymentMethodId,
        BigDecimal price
) implements EnvelopeMessageJsonNode {
    @Override
    public String getType() {
        return "USER_PROMOTION";
    }

    @Override
    public long getId() {
        return userId();
    }
}
