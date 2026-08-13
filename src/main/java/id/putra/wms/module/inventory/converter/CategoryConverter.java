package id.putra.wms.module.inventory.converter;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.model.entity.Category;

/**
 * Converter for Category entity and DTO.
 * Provides static methods for converting between Category entity and CategoryDto.
 */
public class CategoryConverter {

    private CategoryConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Category entity to CategoryDto.
     *
     * @param entity the Category entity
     * @return the CategoryDto, or null if entity is null
     */
    public static CategoryDto toDto(Category entity) {
        if (entity == null) {
            return null;
        }
        
        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    /**
     * Converts a CategoryDto to Category entity.
     * Note: products relationship is ignored and not set from DTO.
     *
     * @param dto the CategoryDto
     * @return the Category entity, or null if dto is null
     */
    public static Category toEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }
        
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        // Products relationship is intentionally not mapped from DTO
        return entity;
    }
}
