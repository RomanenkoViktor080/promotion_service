package school.faang.promotion_service.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import school.faang.promotion_service.entity.user_promotion.UserPromotion;
import school.faang.promotion_service.entity.user_promotion.UserPromotionStatus;
import school.faang.promotion_service.exception.EntityNotFoundException;

public interface UserPromotionRepository extends CrudRepository<UserPromotion, Long> {
    default UserPromotion getByIdOrThrow(long promotionId) {
        return findById(promotionId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Promotion %d not found", promotionId)));
    }

    @Query(nativeQuery = true, value = """
            UPDATE user_promotions SET status = :status, updated_at = now(), used_count = :usedCount
            WHERE id = :promotionId
            """)
    @Modifying
    void updateStatusAndUsedCount(long promotionId, UserPromotionStatus status, long usedCount);
}
