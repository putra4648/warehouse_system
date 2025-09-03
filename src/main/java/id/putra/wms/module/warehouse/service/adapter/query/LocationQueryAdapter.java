package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.model.entity.Location;

public interface LocationQueryAdapter {
    Page<Location> getLocationsByZone(ZoneDto zoneDto, Pageable pageable);

    Page<Location> getLocationsByRack(RackDto rackDto, Pageable pageable);

    Page<Location> getLocationsByWarehouse(WarehouseDto warehouseDto, Pageable pageable);
}
