package id.putra.wms.module.warehouse.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.mapper.LocationMapper;
import id.putra.wms.module.warehouse.model.repository.LocationRepository;
import id.putra.wms.module.warehouse.service.adapter.command.LocationCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class LocationCommandAdapterImpl implements LocationCommandAdapter {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public void add(List<LocationDto> dtos) {
        var entities = dtos.stream().map(locationMapper::toEntity).toList();
        locationRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<LocationDto> dtos) {
        var entities = dtos.stream().map(locationMapper::toEntity).toList();
        locationRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<LocationDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        locationRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }

}
