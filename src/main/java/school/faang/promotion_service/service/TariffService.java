package school.faang.promotion_service.service;

import school.faang.promotion_service.dto.tariff.CreateTariffDto;
import school.faang.promotion_service.dto.tariff.TariffDto;
import school.faang.promotion_service.dto.tariff.UpdateTariffDto;

public interface TariffService {
    TariffDto create(CreateTariffDto dto);

    TariffDto update(long id, UpdateTariffDto dto);
}
