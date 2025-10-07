package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryItemMapper {
    InventoryItemMapper INSTANCE = Mappers.getMapper(InventoryItemMapper.class);
    // InventoryItemDto toDto(InventoryItem entity);
    // InventoryItem toEntity(InventoryItemDto dto);
}
