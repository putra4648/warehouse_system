package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;

public interface RackQueryAdapter {
    Page<RackDto> getRacksByWarehouseAndZone(WarehouseDto dto, ZoneDto zoneDto, Pageable pageable);

    Page<RackDto> getRacksByZone(ZoneDto zoneDto, Pageable pageable);

    RackDto getRackByZone(ZoneDto zoneDto);

    RackDto getRackById(RackDto rackDto);

}
