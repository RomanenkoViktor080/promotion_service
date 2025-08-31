package school.faang.promotion_service.repository;

import org.springframework.data.repository.CrudRepository;
import school.faang.promotion_service.entity.Tariff;
import school.faang.promotion_service.exception.api.EntityNotFoundException;

public interface TariffRepository extends CrudRepository<Tariff, Long> {
    default Tariff getByIdOrThrow(long tariffId) {
        return findById(tariffId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Tariff %d not found", tariffId)));
    }
}
