package school.faang.promotion_service.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import school.faang.promotion_service.exception.api.DataValidationException;

import java.math.BigDecimal;

@Slf4j
@Component
public class PromotionPurchasePolicyImpl implements PromotionPurchasePolicy {

    @Override
    public void validate(BigDecimal balance, BigDecimal price) {

        if (balance.compareTo(price) < 0) {
            log.error("Balance: {}, price: {}", balance, price);
            throw new DataValidationException("Insufficient funds in the account");
        }
    }
}
