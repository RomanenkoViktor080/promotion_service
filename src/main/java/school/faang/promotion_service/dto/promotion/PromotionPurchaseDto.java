package school.faang.promotion_service.dto.promotion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record PromotionPurchaseDto(
        @NotNull
        @Schema(description = "Identifier of the tariff plan to subscribe to")
        Long tariffId,

        @NotNull
        Long paymentMethodId
) {
}
