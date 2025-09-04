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
        zoneRepository.saveAll(dtos.stream().map(zoneMapper::toEntity).toList());
    }

    @Override
    public void update(List<ZoneDto> dtos) {
        zoneRepository.saveAll(dtos.stream().map(zoneMapper::toEntity).toList());
    }

    @Override
    public void delete(List<ZoneDto> dtos) {
        zoneRepository.deleteAllById(dtos.stream().map(d -> d.getId()).toList());
    }

}
