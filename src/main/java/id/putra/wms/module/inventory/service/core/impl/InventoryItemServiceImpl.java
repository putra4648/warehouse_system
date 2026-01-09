package id.putra.wms.module.inventory.service.core.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.module.inventory.service.adapter.command.InventoryItemCommandAdapter;
import id.putra.wms.module.inventory.service.adapter.query.InventoryItemQueryAdapter;
import id.putra.wms.module.inventory.service.core.InventoryItemCoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemCoreService {
    private final InventoryItemQueryAdapter inventoryItemQueryAdapter;
    private final InventoryItemCommandAdapter inventoryItemCommandAdapter;

    @Override
    public Page<InventoryItemDto> getInventoryItems(InventoryItemDto dto, Pageable pageable) {
        return inventoryItemQueryAdapter.getInventoryItems(dto, pageable);
    }

    @Override
    public InventoryItemDto getInventoryItem(InventoryItemDto dto) {
        return inventoryItemQueryAdapter.getInventoryItemById(dto);
    }

    @Override
    public void save(List<InventoryItemDto> dtos) {
        inventoryItemCommandAdapter.save(dtos);
    }

    @Override
    public void update(List<InventoryItemDto> dtos) {
        inventoryItemCommandAdapter.update(dtos);
    }

    @Override
    public void delete(List<InventoryItemDto> dtos) {
        inventoryItemCommandAdapter.delete(dtos);
    }

}
