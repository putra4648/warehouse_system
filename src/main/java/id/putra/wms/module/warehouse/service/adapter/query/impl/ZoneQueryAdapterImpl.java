package id.putra.wms.module.warehouse.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.mapper.ZoneMapper;
import id.putra.wms.module.warehouse.model.repository.ZoneRepository;
import id.putra.wms.module.warehouse.service.adapter.query.ZoneQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZoneQueryAdapterImpl implements ZoneQueryAdapter {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public Page<ZoneDto> getZonesByWarehouse(WarehouseDto dto, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getZonesByWarehouse'");
    }

    @Override
    public ZoneDto getZoneByWarehouse(WarehouseDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getZoneByWarehouse'");
    }

    @Override
    public ZoneDto getZoneDetailById(ZoneDto zoneDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getZoneDetailById'");
    }

}
