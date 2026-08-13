package id.putra.wms.module.warehouse.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.model.entity.Warehouse;

/**
 * Converter for Warehouse entity and DTO.
 * Provides static methods for converting between Warehouse entity and WarehouseDto.
 */
public class WarehouseConverter {

    private WarehouseConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Warehouse entity to WarehouseDto.
     *
     * @param entity the Warehouse entity
     * @return the WarehouseDto, or null if entity is null
     */
    public static WarehouseDto toDto(Warehouse entity) {
        if (entity == null) {
            return null;
        }
        
        return WarehouseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .location(entity.getLocation())
                .build();
    }

    /**
     * Converts a Warehouse list to WarehouseDto list.
     *
     * @param entities list of Warehouse entities
     * @return list of WarehouseDto, or empty list if input is null
     */
    public static List<WarehouseDto> toDtos(List<Warehouse> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(WarehouseConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a WarehouseDto to Warehouse entity.
     *
     * @param dto the WarehouseDto
     * @return the Warehouse entity, or null if dto is null
     */
    public static Warehouse toEntity(WarehouseDto dto) {
        if (dto == null) {
            return null;
        }
        
        Warehouse entity = new Warehouse();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
       
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        // Initialize zones as empty
        entity.setZones(Collections.emptyList());
        
        return entity;
    }
}
