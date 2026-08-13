package id.putra.wms.module.outbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.outbound.dto.PickingTaskDto;
import id.putra.wms.module.outbound.model.entity.PickingTask;

/**
 * Converter for PickingTask entity and DTO.
 * Provides static methods for converting between PickingTask entity and PickingTaskDto.
 */
public class PickingTaskConverter {

    private PickingTaskConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a PickingTask entity to PickingTaskDto.
     *
     * @param entity the PickingTask entity
     * @return the PickingTaskDto, or null if entity is null
     */
    public static PickingTaskDto toDto(PickingTask entity) {
        if (entity == null) {
            return null;
        }
        
        return PickingTaskDto.builder()
                .id(entity.getId())
                .build();
    }

    /**
     * Converts a PickingTask list to PickingTaskDto list.
     *
     * @param entities list of PickingTask entities
     * @return list of PickingTaskDto, or empty list if input is null
     */
    public static List<PickingTaskDto> toDtos(List<PickingTask> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(PickingTaskConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a PickingTaskDto to PickingTask entity.
     *
     * @param dto the PickingTaskDto
     * @return the PickingTask entity, or null if dto is null
     */
    public static PickingTask toEntity(PickingTaskDto dto) {
        if (dto == null) {
            return null;
        }
        
        PickingTask entity = new PickingTask();
        entity.setId(dto.getId());
        return entity;
    }
}
