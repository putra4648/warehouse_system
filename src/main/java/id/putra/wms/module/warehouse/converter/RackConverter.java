package id.putra.wms.module.warehouse.converter;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.model.entity.Rack;

/**
 * Converter for Rack entity and DTO.
 * Provides static methods for converting between Rack entity and RackDto.
 */
public class RackConverter {

    private RackConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Rack entity to RackDto.
     *
     * @param entity the Rack entity
     * @return the RackDto, or null if entity is null
     */
    public static RackDto toDto(Rack entity) {
        if (entity == null) {
            return null;
        }
        
        return RackDto.builder()
                .id(entity.getId())
                .build();
    }

    /**
     * Converts a RackDto to Rack entity.
     *
     * @param dto the RackDto
     * @return the Rack entity, or null if dto is null
     */
    public static Rack toEntity(RackDto dto) {
        if (dto == null) {
            return null;
        }
        
        Rack entity = new Rack();
        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        return entity;
    }
}
