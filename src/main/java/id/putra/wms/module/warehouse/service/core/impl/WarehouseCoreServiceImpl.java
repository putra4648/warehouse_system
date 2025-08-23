package id.putra.wms.module.warehouse.service.core.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import id.putra.wms.shared.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseCoreServiceImpl implements WarehouseCoreService {

    private final WarehouseQueryAdapter warehouseQueryAdapter;

    private final WarehouseCommandAdapter warehouseCommandAdapter;

    @Override
    public Page<WarehouseDto> getAll(Pageable pageable) {
        return warehouseQueryAdapter.getAll(pageable);
    }

    @Override
    public WarehouseDto getById(String id) {
        return warehouseQueryAdapter
                .getById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(WarehouseDto req) {
        warehouseCommandAdapter.save(req);
    }

    @Override
    public ZoneDto getZoneById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getZoneById'");
    }

    @Override
    public RackDto getRackById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRackById'");
    }

    @Override
    public LocationDto getLocationById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocationById'");
    }
}
