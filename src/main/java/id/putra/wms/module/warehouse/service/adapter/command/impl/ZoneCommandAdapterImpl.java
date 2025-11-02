package id.putra.wms.module.warehouse.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.mapper.ZoneMapper;
import id.putra.wms.module.warehouse.model.repository.ZoneRepository;
import id.putra.wms.module.warehouse.service.adapter.command.ZoneCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ZoneCommandAdapterImpl implements ZoneCommandAdapter {
    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public void add(List<ZoneDto> dtos) {
        var entities = dtos.stream().map(zoneMapper::toEntity).toList();
        zoneRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<ZoneDto> dtos) {
        var entities = dtos.stream().map(zoneMapper::toEntity).toList();
        zoneRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<ZoneDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        zoneRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }

}
