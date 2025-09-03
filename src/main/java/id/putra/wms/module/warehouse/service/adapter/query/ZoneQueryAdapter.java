package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;

public interface ZoneQueryAdapter {
    Page<ZoneDto> getZonesByWarehouse(WarehouseDto dto, Pageable pageable);

    ZoneDto getZoneByWarehouse(WarehouseDto dto);

    ZoneDto getZoneDetailById(ZoneDto zoneDto);
}
