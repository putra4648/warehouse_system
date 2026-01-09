package id.putra.wms.module.inventory.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.inventory.dto.InventoryItemDto;

public interface InventoryItemQueryAdapter {

    Page<InventoryItemDto> getInventoryItems(InventoryItemDto dto, Pageable pageable);

    InventoryItemDto getInventoryItemById(InventoryItemDto dto);

}
