package id.putra.wms.module.outbound.service.adapter.query;

import java.util.List;
import id.putra.wms.module.outbound.dto.CarrierDto;

public interface CarrierQueryAdapter {
    CarrierDto getById(Long id);
    List<CarrierDto> getAll(CarrierDto filter);
}
