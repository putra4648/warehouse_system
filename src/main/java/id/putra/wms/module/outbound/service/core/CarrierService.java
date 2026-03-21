package id.putra.wms.module.outbound.service.core;

import java.util.List;
import id.putra.wms.module.outbound.dto.CarrierDto;

public interface CarrierService {
    CarrierDto create(CarrierDto dto);
    CarrierDto update(CarrierDto dto);
    Boolean delete(Long id);
    CarrierDto getById(Long id);
    List<CarrierDto> getAll(CarrierDto filter);
}
