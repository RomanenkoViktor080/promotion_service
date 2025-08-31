package school.faang.promotion_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.promotion_service.dto.tariff.CreateTariffDto;
import school.faang.promotion_service.dto.tariff.TariffDto;
import school.faang.promotion_service.dto.tariff.UpdateTariffDto;
import school.faang.promotion_service.entity.Tariff;
import school.faang.promotion_service.kafka.producer.TariffProducer;
import school.faang.promotion_service.mapper.TariffMapper;
import school.faang.promotion_service.repository.TariffRepository;

@RequiredArgsConstructor
@Service
public class TariffServiceImpl implements TariffService {
    private final TariffMapper mapper;
    private final TariffRepository repository;
    private final TariffProducer tariffProducer;

    @Override
    public TariffDto create(CreateTariffDto dto) {
        Tariff tariff = repository.save(mapper.toTariff(dto));

        tariffProducer.onCreate(tariff.getId(), mapper.toCreateTariffEvent(tariff));

        return mapper.toTariffDto(tariff);
    }

    @Override
    public TariffDto update(long id, UpdateTariffDto dto) {
        Tariff tariff = repository.getByIdOrThrow(id);

        mapper.update(dto, tariff);
        tariff = repository.save(tariff);
        tariffProducer.onUpdate(mapper.toUpdateTariffEvent(tariff));

        return mapper.toTariffDto(tariff);
    }
}
