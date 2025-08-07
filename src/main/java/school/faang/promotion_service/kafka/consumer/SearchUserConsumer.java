package school.faang.promotion_service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import school.faang.promotion_service.kafka.dto.UserViewDto;
import school.faang.promotion_service.service.PromotionCounterService;

@Slf4j
@RequiredArgsConstructor
@Component
public class SearchUserConsumer {
    private final PromotionCounterService promotionCounterService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "search.user.impression.events")
    public void handle(String data) throws JsonProcessingException {
        promotionCountView(objectMapper.readValue(
                data,
                UserViewDto.class
        ));
    }

    private void promotionCountView(UserViewDto dto) {
        promotionCounterService.onView(dto);
    }
}
