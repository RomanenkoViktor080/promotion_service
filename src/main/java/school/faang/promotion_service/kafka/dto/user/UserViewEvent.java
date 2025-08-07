package school.faang.promotion_service.kafka.dto.user;

public record UserViewEvent(
        long userId,
        long promotionId
) {
}
