package school.faang.promotion_service.service;

import school.faang.promotion_service.dto.promotion.PromotionPurchaseDto;
import school.faang.promotion_service.kafka.dto.PromotionPurchaseCompleteEvent;

public interface PromotionPurchaseService {
    void purchase(PromotionPurchaseDto dto);

    void promote(PromotionPurchaseCompleteEvent dto);
}
