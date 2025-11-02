package id.putra.wms.module.warehouse.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.mapper.ZoneMapper;
import id.putra.wms.module.warehouse.model.entity.Zone;
import id.putra.wms.module.warehouse.model.repository.ZoneRepository;
import id.putra.wms.module.warehouse.service.adapter.query.ZoneQueryAdapter;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ZoneQueryAdapterImpl implements ZoneQueryAdapter {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public Page<ZoneDto> getZones(ZoneDto dto, Pageable pageable) {
        Specification<Zone> specs = (root, criteria, builder) -> {
            Predicate name = builder.like(root.get("name"),
                    "%" + (StringUtils.hasText(dto.getName()) ? dto.getName() : "") + "%");
            return builder.and(name);
        };
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return zoneRepository.findAll(specs, safePageable).map(zoneMapper::toZoneDto);
    }

    @Override
    public ZoneDto getZone(ZoneDto dto) {
        String safeId = java.util.Objects.requireNonNull(dto.getId());
        var z = zoneRepository.findById(safeId);
        return z.map(zoneMapper::toZoneDto).orElse(null);
    }

}
