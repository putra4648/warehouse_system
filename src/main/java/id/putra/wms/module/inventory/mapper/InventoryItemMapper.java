package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.module.inventory.model.entity.InventoryItem;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryItemMapper {
    InventoryItemDto toDto(InventoryItem entity);

    InventoryItem toEntity(InventoryItemDto dto);
}
