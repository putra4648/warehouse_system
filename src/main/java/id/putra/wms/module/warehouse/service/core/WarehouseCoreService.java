package id.putra.wms.module.warehouse.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;

public interface WarehouseCoreService {

    Page<WarehouseDto> getAll(Pageable pageable);

    WarehouseDto getById(String id);

    ZoneDto getZoneById(String id);

    RackDto getRackById(String id);

    LocationDto getLocationById(String id);

    void save(WarehouseDto dto);

}
