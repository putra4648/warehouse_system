package id.putra.wms.module.inbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.module.inbound.model.entity.QualityInspection;

/**
 * Converter for QualityInspection entity and DTO.
 * Provides static methods for converting between QualityInspection entity and QualityInspectionDto.
 */
public class QualityInspectionConverter {

    private QualityInspectionConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a QualityInspection entity to QualityInspectionDto.
     *
     * @param entity the QualityInspection entity
     * @return the QualityInspectionDto, or null if entity is null
     */
    public static QualityInspectionDto toDto(QualityInspection entity) {
        if (entity == null) {
            return null;
        }
        
        return QualityInspectionDto.builder()
                .id(entity.getId())
                .build();
    }

    /**
     * Converts a QualityInspection list to QualityInspectionDto list.
     *
     * @param entities list of QualityInspection entities
     * @return list of QualityInspectionDto, or empty list if input is null
     */
    public static List<QualityInspectionDto> toDtos(List<QualityInspection> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(QualityInspectionConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a QualityInspectionDto to QualityInspection entity.
     *
     * @param dto the QualityInspectionDto
     * @return the QualityInspection entity, or null if dto is null
     */
    public static QualityInspection toEntity(QualityInspectionDto dto) {
        if (dto == null) {
            return null;
        }
        
        QualityInspection entity = new QualityInspection();
        entity.setId(dto.getId());
        return entity;
    }
}
