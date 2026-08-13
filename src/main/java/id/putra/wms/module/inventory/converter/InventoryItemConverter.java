package id.putra.wms.module.inventory.converter;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.module.inventory.model.entity.InventoryItem;

/**
 * Converter for InventoryItem entity and DTO.
 * Provides static methods for converting between InventoryItem entity and InventoryItemDto.
 */
public class InventoryItemConverter {

    private InventoryItemConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts an InventoryItem entity to InventoryItemDto.
     *
     * @param entity the InventoryItem entity
     * @return the InventoryItemDto, or null if entity is null
     */
    public static InventoryItemDto toDto(InventoryItem entity) {
        if (entity == null) {
            return null;
        }
        
        return InventoryItemDto.builder()
                .id(entity.getId())
                .quantity(entity.getQuantity())
                .build();
    }

    /**
     * Converts an InventoryItemDto to InventoryItem entity.
     *
     * @param dto the InventoryItemDto
     * @return the InventoryItem entity, or null if dto is null
     */
    public static InventoryItem toEntity(InventoryItemDto dto) {
        if (dto == null) {
            return null;
        }
        
        InventoryItem entity = new InventoryItem();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
}
