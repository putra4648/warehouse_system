package id.putra.wms.module.inventory.service.adapter.command;

import java.util.List;

import id.putra.wms.module.inventory.dto.InventoryItemDto;

public interface InventoryItemCommandAdapter {

    void save(List<InventoryItemDto> dtos);

    void update(List<InventoryItemDto> dtos);

    void delete(List<InventoryItemDto> dtos);

}
