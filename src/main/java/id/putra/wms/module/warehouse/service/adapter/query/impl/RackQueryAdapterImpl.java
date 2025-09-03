package id.putra.wms.module.warehouse.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.mapper.RackMapper;
import id.putra.wms.module.warehouse.model.repository.RackRepository;
import id.putra.wms.module.warehouse.service.adapter.query.RackQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RackQueryAdapterImpl implements RackQueryAdapter {
    private final RackRepository rackRepository;
    private final RackMapper rackMapper;

    @Override
    public Page<RackDto> getRacksByWarehouseAndZone(WarehouseDto dto, ZoneDto zoneDto, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRacksByWarehouseAndZone'");
    }

    @Override
    public Page<RackDto> getRacksByZone(ZoneDto zoneDto, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRacksByZone'");
    }

    @Override
    public RackDto getRackByZone(ZoneDto zoneDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRackByZone'");
    }

    @Override
    public RackDto getRackById(RackDto rackDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRackById'");
    }

}
