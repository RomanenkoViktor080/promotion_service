package school.faang.promotion_service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import school.faang.promotion_service.kafka.EnvelopeMessage;
import school.faang.promotion_service.kafka.dto.PromotionPurchaseCompleteEvent;
import school.faang.promotion_service.service.PromotionPurchaseService;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserPurchaseConsumerImpl {
    private final PromotionPurchaseService promotionPurchaseService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "account.transaction.events")
    public void handle(String data) throws JsonProcessingException {
        EnvelopeMessage envelopeMessage = objectMapper.readValue(data, EnvelopeMessage.class);
        switch (envelopeMessage.type()) {
          case ("USER_PROMOTION_PURCHASE_COMPLETE"): {
              promotionPurchaseComplete(objectMapper.treeToValue(
                      envelopeMessage.payload(),
                      PromotionPurchaseCompleteEvent.class
              ));
              break;
          }
          default: {
              throw new RuntimeException("Event not found");
          }
        }
    }

    private void promotionPurchaseComplete(PromotionPurchaseCompleteEvent dto) {
        promotionPurchaseService.promote(dto);
    }
}
