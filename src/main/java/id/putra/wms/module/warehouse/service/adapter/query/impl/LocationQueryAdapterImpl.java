package id.putra.wms.module.warehouse.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.mapper.LocationMapper;
import id.putra.wms.module.warehouse.model.entity.Location;
import id.putra.wms.module.warehouse.model.repository.LocationRepository;
import id.putra.wms.module.warehouse.service.adapter.query.LocationQueryAdapter;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationQueryAdapterImpl implements LocationQueryAdapter {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public Page<LocationDto> getLocations(LocationDto dto, Pageable pageable) {
        Specification<Location> specs = (root, criteria, builder) -> {
            Predicate name = builder.like(root.get("name"),
                    "%" + (StringUtils.hasText(dto.getName()) ? dto.getName() : "") + "%");
            return builder.and(name);
        };
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return locationRepository.findAll(specs, safePageable).map(locationMapper::toDto);
    }

    @Override
    public LocationDto getLocation(LocationDto dto) {
        Long safeId = java.util.Objects.requireNonNull(dto.getId());
        return locationRepository.findById(safeId).map(locationMapper::toDto).orElse(null);
    }

}
