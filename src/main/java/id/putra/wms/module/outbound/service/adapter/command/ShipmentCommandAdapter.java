package id.putra.wms.module.outbound.service.adapter.command;

import java.util.List;
import id.putra.wms.module.outbound.dto.ShipmentDto;

public interface ShipmentCommandAdapter {
    void add(List<ShipmentDto> dtos);
    void update(List<ShipmentDto> dtos);
    void delete(List<ShipmentDto> dtos);
}
