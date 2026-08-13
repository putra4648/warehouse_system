package id.putra.wms.shared.converter;

import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.base.entity.Product;

/**
 * Converter for Product entity and DTO.
 * Provides static methods for converting between Product entity and ProductDto.
 */
public class ProductConverter {

    private ProductConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Product entity to ProductDto.
     *
     * @param entity the Product entity
     * @return the ProductDto, or null if entity is null
     */
    public static ProductDto toDto(Product entity) {
        if (entity == null) {
            return null;
        }
        
        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .sku(entity.getSku())
                .description(entity.getDescription())
                .build();
    }

    /**
     * Converts a ProductDto to Product entity.
     *
     * @param dto the ProductDto
     * @return the Product entity, or null if dto is null
     */
    public static Product toEntity(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSku(dto.getSku());
        entity.setDescription(dto.getDescription());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
   
        return entity;
    }
}
