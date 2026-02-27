package id.putra.wms.module.inventory.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.module.inventory.model.repository.InventoryItemRepository;
import id.putra.wms.module.inventory.service.adapter.command.InventoryItemCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class InventoryItemCommandAdapterImpl implements InventoryItemCommandAdapter {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    public void save(List<InventoryItemDto> dtos) {
        var entities = dtos.stream().map(d -> inventoryItemMapper.toEntity(d)).toList();
        inventoryItemRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<InventoryItemDto> dtos) {
        var entities = dtos.stream().map(d -> inventoryItemMapper.toEntity(d)).toList();
        inventoryItemRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<InventoryItemDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        inventoryItemRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }

}
