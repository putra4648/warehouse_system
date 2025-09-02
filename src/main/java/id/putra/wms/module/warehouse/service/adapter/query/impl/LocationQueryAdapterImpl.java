package id.putra.wms.module.warehouse.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.mapper.LocationMapper;
import id.putra.wms.module.warehouse.model.entity.Location;
import id.putra.wms.module.warehouse.model.repository.LocationRepository;
import id.putra.wms.module.warehouse.service.adapter.query.LocationQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationQueryAdapterImpl implements LocationQueryAdapter {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public Page<Location> getLocationsByZone(ZoneDto zoneDto, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocationsByZone'");
    }

    @Override
    public Page<Location> getLocationsByRack(RackDto rackDto, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocationsByRack'");
    }

    @Override
    public Page<Location> getLocationsByWarehouse(WarehouseDto warehouseDto, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocationsByWarehouse'");
    }

}
