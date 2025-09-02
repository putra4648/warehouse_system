package id.putra.wms.module.inventory.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseService implements WarehouseCoreService {

    private final WarehouseQueryAdapter warehouseQueryAdapter;
    private final WarehouseCommandAdapter warehouseCommandAdapter;

    @Override
    public Page<WarehouseDto> getWarehouses(WarehouseDto dto, Pageable pageable) {
        return warehouseQueryAdapter.getWarehouses(dto, pageable);
    }

    @Override
    public void save(WarehouseDto req) {
        warehouseCommandAdapter.save(req);
    }

    @Override
    public Page<RackDto> getRacks(WarehouseDto dto, ZoneDto zoneDto, Pageable pageable) {
        // return warehouseQueryAdapter.getRacksByWarehouseAndZone(dto, zoneDto,
        // pageable);
        throw new UnsupportedOperationException("Unimplemented method 'getRacks'");
    }

    @Override
    public Page<ZoneDto> getZonesByWarehouseId(WarehouseDto dto, Pageable pageable) {
        // return warehouseQueryAdapter.getZonesByWarehouseId(dto, pageable);
        throw new UnsupportedOperationException("Unimplemented method 'getZonesByWarehouseId'");

    }

    @Override
    public WarehouseDto getWarehouseById(WarehouseDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWarehouseById'");
    }

    @Override
    public ZoneDto getZoneByIdByWarehouseId(WarehouseDto dto, ZoneDto zoneDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getZoneByIdByWarehouseId'");
    }

    @Override
    public RackDto getLocationByRack(WarehouseDto dto, ZoneDto zoneDto, RackDto rackDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocationByRack'");
    }

}
