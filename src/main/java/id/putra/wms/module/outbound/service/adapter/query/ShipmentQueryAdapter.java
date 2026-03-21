package id.putra.wms.module.outbound.service.adapter.query;

import java.util.List;
import id.putra.wms.module.outbound.dto.ShipmentDto;

public interface ShipmentQueryAdapter {
    ShipmentDto getById(Long id);
    List<ShipmentDto> getAll(ShipmentDto filter);
}
