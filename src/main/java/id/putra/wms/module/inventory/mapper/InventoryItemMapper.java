package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.InjectionStrategy;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.module.inventory.model.entity.InventoryItem;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InventoryItemMapper {
    InventoryItemDto toDto(InventoryItem entity);

    InventoryItem toEntity(InventoryItemDto dto);
}
