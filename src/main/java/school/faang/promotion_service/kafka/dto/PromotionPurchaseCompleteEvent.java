package school.faang.promotion_service.kafka.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PromotionPurchaseCompleteEvent(
        long userId,
        long tariffId,
        long promotionCount,
        long paymentMethodId,
        BigDecimal price
) {
}
