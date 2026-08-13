package id.putra.wms.module.outbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.outbound.dto.SalesOrderLineDto;
import id.putra.wms.module.outbound.model.entity.SalesOrderLine;

/**
 * Converter for SalesOrderLine entity and DTO.
 * Provides static methods for converting between SalesOrderLine entity and SalesOrderLineDto.
 */
public class SalesOrderLineConverter {

    private SalesOrderLineConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a SalesOrderLine entity to SalesOrderLineDto.
     *
     * @param entity the SalesOrderLine entity
     * @return the SalesOrderLineDto, or null if entity is null
     */
    public static SalesOrderLineDto toDto(SalesOrderLine entity) {
        if (entity == null) {
            return null;
        }
        
        return SalesOrderLineDto.builder()
                .id(entity.getId())
                .productId(entity.getProduct() != null ? entity.getProduct().getId() : null)
                .build();
    }

    /**
     * Converts a SalesOrderLine list to SalesOrderLineDto list.
     *
     * @param entities list of SalesOrderLine entities
     * @return list of SalesOrderLineDto, or empty list if input is null
     */
    public static List<SalesOrderLineDto> toDtos(List<SalesOrderLine> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(SalesOrderLineConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a SalesOrderLineDto to SalesOrderLine entity.
     *
     * @param dto the SalesOrderLineDto
     * @return the SalesOrderLine entity, or null if dto is null
     */
    public static SalesOrderLine toEntity(SalesOrderLineDto dto) {
        if (dto == null) {
            return null;
        }
        
        SalesOrderLine entity = new SalesOrderLine();
        entity.setId(dto.getId());
        return entity;
    }
}
