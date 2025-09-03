package id.putra.wms.module.warehouse.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;

public interface RackCoreService {
    Page<RackDto> getRacks(WarehouseDto dto, ZoneDto zoneDto, Pageable pageable);

    RackDto getRack(RackDto rackDto);
}
