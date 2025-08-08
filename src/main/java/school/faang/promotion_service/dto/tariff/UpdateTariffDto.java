package school.faang.promotion_service.dto.tariff;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateTariffDto(
        @NotBlank(message = "Title required")
        @Schema(description = "Tariff title")
        String title,

        @NotNull(message = "Tariff boost factor is required")
        @Schema(
                description = "Tariff boost factor. Affects user promotion in search results",
                example = "1.25"
        )
        Double boostFactor,

        @Schema(description = "Tariff description")
        String description,

        @NotNull(message = "Promotion count required")
        @Schema(description = "Tariff promotion count")
        long promotionCount,

        @NotNull(message = "Base price required")
        @Schema(description = "Tariff base price")
        BigDecimal basePrice,

        @NotNull(message = "Duration required")
        @Schema(description = "Tariff days duration")
        int durationDays,

        @Schema(description = "Is tariff active")
        boolean active
) {
}
