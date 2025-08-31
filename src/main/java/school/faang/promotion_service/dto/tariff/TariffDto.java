package school.faang.promotion_service.dto.tariff;

import java.math.BigDecimal;

public record TariffDto(
        long id,
        String title,
        String description,
        long promotionCount,
        BigDecimal basePrice,
        int durationDays
) {
}
