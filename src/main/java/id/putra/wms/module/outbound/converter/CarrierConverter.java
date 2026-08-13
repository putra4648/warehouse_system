package id.putra.wms.module.outbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.outbound.dto.CarrierDto;
import id.putra.wms.module.outbound.model.entity.Carrier;

/**
 * Converter for Carrier entity and DTO.
 * Provides static methods for converting between Carrier entity and CarrierDto.
 */
public class CarrierConverter {

    private CarrierConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Carrier entity to CarrierDto.
     *
     * @param entity the Carrier entity
     * @return the CarrierDto, or null if entity is null
     */
    public static CarrierDto toDto(Carrier entity) {
        if (entity == null) {
            return null;
        }
        
        return CarrierDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    /**
     * Converts a Carrier list to CarrierDto list.
     *
     * @param entities list of Carrier entities
     * @return list of CarrierDto, or empty list if input is null
     */
    public static List<CarrierDto> toDtos(List<Carrier> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(CarrierConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a CarrierDto to Carrier entity.
     * Note: shipments relationship is ignored and not set from DTO.
     *
     * @param dto the CarrierDto
     * @return the Carrier entity, or null if dto is null
     */
    public static Carrier toEntity(CarrierDto dto) {
        if (dto == null) {
            return null;
        }
        
        Carrier entity = new Carrier();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        // Shipments relationship is intentionally not mapped from DTO
        return entity;
    }
}
