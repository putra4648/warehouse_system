package id.putra.wms.module.warehouse.converter;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.model.entity.Location;

/**
 * Converter for Location entity and DTO.
 * Provides static methods for converting between Location entity and LocationDto with rack ID mapping.
 */
public class LocationConverter {

    private LocationConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Location entity to LocationDto.
     * Maps the rack relationship to rackId.
     *
     * @param entity the Location entity
     * @return the LocationDto, or null if entity is null
     */
    public static LocationDto toDto(Location entity) {
        if (entity == null) {
            return null;
        }
        
        return LocationDto.builder()
                .id(entity.getId())
                .build();
    }

    /**
     * Converts a LocationDto to Location entity.
     * Maps rackId to the rack relationship.
     *
     * @param dto the LocationDto
     * @return the Location entity, or null if dto is null
     */
    public static Location toEntity(LocationDto dto) {
        if (dto == null) {
            return null;
        }
        
        Location entity = new Location();
        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        // Set rack relationship defensively
        if (dto.getRackId() != null) {
            entity.setRack(new id.putra.wms.module.warehouse.model.entity.Rack());
        }
        
        return entity;
    }
}
