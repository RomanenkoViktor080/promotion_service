package school.faang.promotion_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import school.faang.avro.tariff.CreateTariffEvent;
import school.faang.avro.tariff.UpdateTariffEvent;
import school.faang.promotion_service.dto.tariff.CreateTariffDto;
import school.faang.promotion_service.dto.tariff.TariffDto;
import school.faang.promotion_service.dto.tariff.UpdateTariffDto;
import school.faang.promotion_service.entity.Tariff;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface TariffMapper {
    Tariff toTariff(CreateTariffDto dto);

    TariffDto toTariffDto(Tariff tariff);

    CreateTariffEvent toCreateTariffEvent(Tariff tariff);

    UpdateTariffEvent toUpdateTariffEvent(Tariff tariff);

    void update(UpdateTariffDto dto, @MappingTarget Tariff entity);
}
