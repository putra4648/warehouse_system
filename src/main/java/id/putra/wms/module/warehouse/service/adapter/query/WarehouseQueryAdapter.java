package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;

public interface WarehouseQueryAdapter {

    Page<WarehouseDto> getWarehouses(WarehouseDto dto, Pageable pageable);

    Page<RackDto> getRacks(WarehouseDto dto, ZoneDto zoneDto, Pageable pageable);

    Page<ZoneDto> getZonesByWarehouseId(WarehouseDto dto, Pageable pageable);

    WarehouseDto getWarehouseById(WarehouseDto dto);

    ZoneDto getZoneByIdByWarehouseId(WarehouseDto dto, ZoneDto zoneDto);

    RackDto getLocationByRack(WarehouseDto dto, ZoneDto zoneDto, RackDto rackDto);

}
