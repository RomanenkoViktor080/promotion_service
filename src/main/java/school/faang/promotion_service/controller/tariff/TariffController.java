package school.faang.promotion_service.controller.tariff;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.faang.promotion_service.dto.tariff.CreateTariffDto;
import school.faang.promotion_service.dto.tariff.TariffDto;
import school.faang.promotion_service.dto.tariff.UpdateTariffDto;
import school.faang.promotion_service.service.TariffService;

@RequiredArgsConstructor
@RequestMapping("/api/v1/tariffs")
@RestController
@Tag(name = "Tariff")
public class TariffController {
    private final TariffService tariffService;

    @PostMapping
    public ResponseEntity<TariffDto> create(@RequestBody @Valid CreateTariffDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tariffService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TariffDto> update(@PathVariable long id, @RequestBody @Valid UpdateTariffDto dto) {
        return ResponseEntity.ok(tariffService.update(id, dto));
    }
}
