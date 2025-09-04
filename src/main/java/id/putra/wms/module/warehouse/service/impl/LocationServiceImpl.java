package id.putra.wms.module.warehouse.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.service.adapter.command.LocationCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.LocationQueryAdapter;
import id.putra.wms.module.warehouse.service.core.LocationCoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationCoreService {
    private final LocationQueryAdapter locationQueryAdapter;
    private final LocationCommandAdapter locationCommandAdapter;

    @Override
    public Page<LocationDto> getLocations(LocationDto dto, Pageable pageable) {
        return locationQueryAdapter.getLocations(dto, pageable);
    }

    @Override
    public LocationDto getLocation(LocationDto dto) {
        return locationQueryAdapter.getLocation(dto);
    }

    @Override
    public void add(List<LocationDto> dtos) {
        locationCommandAdapter.add(dtos);
    }

    @Override
    public void update(List<LocationDto> dtos) {
        locationCommandAdapter.update(dtos);
    }

    @Override
    public void delete(List<LocationDto> dtos) {
        locationCommandAdapter.delete(dtos);
    }

}
