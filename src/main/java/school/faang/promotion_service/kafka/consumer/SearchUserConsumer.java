package school.faang.promotion_service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import school.faang.avro.user.UserViewEvent;
import school.faang.promotion_service.service.PromotionCounterService;

@Slf4j
@RequiredArgsConstructor
@KafkaListener(topics = "${spring.kafka.topics.search-users-impression.name}")
@Component
public class SearchUserConsumer {
    private final PromotionCounterService promotionCounterService;
    
    @KafkaHandler
    public void handle(UserViewEvent dto) throws JsonProcessingException {
        log.info("Search users impression event, data: {}", dto);
        promotionCounterService.onView(dto);
    }
}
