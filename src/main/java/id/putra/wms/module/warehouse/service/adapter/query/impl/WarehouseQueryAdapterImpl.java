package id.putra.wms.module.warehouse.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.mapper.WarehouseMapper;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.module.warehouse.model.repository.WarehouseRepository;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseQueryAdapterImpl implements WarehouseQueryAdapter {

    // private final JdbcTemplate jdbcTemplate;
    // private final TaskExecutor virtualThreadExecutor;

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public WarehouseDto getWarehouseById(WarehouseDto dto) {
        var wh = warehouseRepository.findById(dto.getId());
        return wh.map(warehouseMapper::toDto).orElse(null);
    }

    @Override
    public Page<WarehouseDto> getWarehouses(WarehouseDto dto, Pageable pageable) {
        Specification<Warehouse> specs = (root, criteria, builder) -> {
            Predicate name = builder.like(root.get("name"),
                    "%" + (StringUtils.hasText(dto.getName()) ? dto.getName() : "") + "%");
            return builder.and(name);
        };
        return warehouseRepository.findAll(specs, pageable).map(warehouseMapper::toDto);
    }

}
