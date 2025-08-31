package school.faang.promotion_service.controller.promotion_purchase;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.faang.promotion_service.dto.promotion.PromotionPurchaseDto;
import school.faang.promotion_service.service.PromotionPurchaseService;

@RequiredArgsConstructor
@RestController
@Tag(name = "Promotion purchases")
@RequestMapping("/api/v1/promotion-purchases")
public class PromotionPurchaseController {
    private final PromotionPurchaseService service;

    @PostMapping
    public ResponseEntity<Void> purchase(@RequestBody @Valid PromotionPurchaseDto dto) {
        service.purchase(dto);

        return ResponseEntity.accepted()
                .build();
    }
}
