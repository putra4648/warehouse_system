package id.putra.wms.module.warehouse.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.service.adapter.command.ZoneCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.ZoneQueryAdapter;
import id.putra.wms.module.warehouse.service.core.ZoneCoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneCoreService {
    private final ZoneQueryAdapter zoneQueryAdapter;
    private final ZoneCommandAdapter zoneCommandAdapter;

    @Override
    public Page<ZoneDto> getZones(ZoneDto dto, Pageable pageable) {
        return zoneQueryAdapter.getZones(dto, pageable);
    }

    @Override
    public ZoneDto getZone(ZoneDto zoneDto) {
        return zoneQueryAdapter.getZone(zoneDto);
    }

    @Override
    public void add(List<ZoneDto> dtos) {
        zoneCommandAdapter.add(dtos);
    }

    @Override
    public void update(List<ZoneDto> dtos) {
        zoneCommandAdapter.update(dtos);
    }

    @Override
    public void delete(List<ZoneDto> dtos) {
        zoneCommandAdapter.delete(dtos);
    }

}
