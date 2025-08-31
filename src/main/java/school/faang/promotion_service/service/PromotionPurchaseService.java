package school.faang.promotion_service.service;

import school.faang.avro.promotion.PromotionPurchaseCompleteEvent;
import school.faang.promotion_service.dto.promotion.PromotionPurchaseDto;

public interface PromotionPurchaseService {
    void purchase(PromotionPurchaseDto dto);

    void promote(PromotionPurchaseCompleteEvent dto);
}
