package id.putra.wms.module.inventory.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.module.inventory.model.entity.InventoryItem;
import id.putra.wms.module.inventory.model.repository.InventoryItemRepository;
import id.putra.wms.module.inventory.service.adapter.query.InventoryItemQueryAdapter;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryItemQueryAdapterImpl implements InventoryItemQueryAdapter {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    public InventoryItemDto getInventoryItemById(InventoryItemDto dto) {
        Long id = java.util.Objects.requireNonNull(dto.getId());
        var item = inventoryItemRepository.findById(id);
        return item.map(inventoryItemMapper::toDto).orElse(null);
    }

    @Override
    public Page<InventoryItemDto> getInventoryItems(InventoryItemDto dto, Pageable pageable) {
        Specification<InventoryItem> specs = (root, criteria, builder) -> {
            Predicate name = builder.like(root.get("name"),
                    "%" + (StringUtils.hasText(dto.getName()) ? dto.getName() : "") + "%");
            return builder.and(name);
        };
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return inventoryItemRepository.findAll(specs, safePageable).map(inventoryItemMapper::toDto);
    }

}
