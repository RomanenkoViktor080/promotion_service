package school.faang.promotion_service.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import school.faang.avro.promotion.PromotionPurchaseCompleteEvent;
import school.faang.promotion_service.service.PromotionPurchaseService;

@RequiredArgsConstructor
@Component
@KafkaListener(topics = "${spring.kafka.topics.account-transactions-events.name}")
public class UserPurchaseConsumerImpl {
    private final PromotionPurchaseService promotionPurchaseService;

    @KafkaHandler
    public void handle(PromotionPurchaseCompleteEvent data) {
        promotionPurchaseService.promote(data);
    }
}
