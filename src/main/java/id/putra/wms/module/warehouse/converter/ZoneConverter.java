package id.putra.wms.module.warehouse.converter;

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.model.entity.Zone;

/**
 * Converter for Zone entity and DTO.
 * Provides static methods for converting between Zone entity and ZoneDto with warehouse ID mapping.
 */
public class ZoneConverter {

    private ZoneConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Zone entity to ZoneDto.
     * Maps the warehouse relationship to warehouseId.
     *
     * @param entity the Zone entity
     * @return the ZoneDto, or null if entity is null
     */
    public static ZoneDto toDto(Zone entity) {
        if (entity == null) {
            return null;
        }
        
        return ZoneDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .build();
    }

    /**
     * Converts a ZoneDto to Zone entity.
     * Maps warehouseId to the warehouse relationship.
     *
     * @param dto the ZoneDto
     * @return the Zone entity, or null if dto is null
     */
    public static Zone toEntity(ZoneDto dto) {
        if (dto == null) {
            return null;
        }
        
        Zone entity = new Zone();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        // Set warehouse relationship defensively
        if (dto.getWarehouseId() != null) {
            entity.setWarehouse(new id.putra.wms.module.warehouse.model.entity.Warehouse());
        }
        
        return entity;
    }
}
