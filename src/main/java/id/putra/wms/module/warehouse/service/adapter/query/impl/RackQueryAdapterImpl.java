package id.putra.wms.module.warehouse.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.mapper.RackMapper;
import id.putra.wms.module.warehouse.model.entity.Rack;
import id.putra.wms.module.warehouse.model.repository.RackRepository;
import id.putra.wms.module.warehouse.service.adapter.query.RackQueryAdapter;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RackQueryAdapterImpl implements RackQueryAdapter {
    private final RackRepository rackRepository;
    private final RackMapper rackMapper;

    @Override
    public Page<RackDto> getRacks(RackDto dto, Pageable pageable) {
        Specification<Rack> specs = (root, criteria, builder) -> {
            Predicate name = builder.like(root.get("name"),
                    "%" + (StringUtils.hasText(dto.getName()) ? dto.getName() : "") + "%");
            return builder.and(name);
        };

        return rackRepository.findAll(specs, pageable).map(rackMapper::toDto);
    }

    @Override
    public RackDto getRackById(RackDto rackDto) {
        return rackRepository.findById(rackDto.getId()).map(rackMapper::toDto).orElseGet(null);
    }

}
