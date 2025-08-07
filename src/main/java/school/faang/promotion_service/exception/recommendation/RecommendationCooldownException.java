package school.faang.promotion_service.exception.recommendation;

public class RecommendationCooldownException extends RuntimeException {
    public RecommendationCooldownException(String message) {
        super(message);
    }
}
