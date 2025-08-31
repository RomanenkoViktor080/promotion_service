package school.faang.promotion_service.policy;

import java.math.BigDecimal;

public interface PromotionPurchasePolicy {
    void validate(BigDecimal balance, BigDecimal price);
}
