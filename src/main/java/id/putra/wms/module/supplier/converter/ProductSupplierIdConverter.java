package id.putra.wms.module.supplier.converter;

import id.putra.wms.module.supplier.dto.ProductSupplierIdDto;
import id.putra.wms.module.supplier.model.entity.ProductSupplierId;

/**
 * Conventional DTO converter for ProductSupplierId
 * Handles conversion between entity and DTO with safe null handling
 */
public class ProductSupplierIdConverter {

    private ProductSupplierIdConverter() {
        // Utility class
    }

    /**
     * Converts ProductSupplierId entity to DTO
     * @param entity the entity to convert
     * @return the DTO, or null if entity is null
     */
    public static ProductSupplierIdDto toDto(ProductSupplierId entity) {
        if (entity == null) {
            return null;
        }
        ProductSupplierIdDto dto = new ProductSupplierIdDto();
        dto.setProductId(entity.getProductId());
        dto.setSupplierId(entity.getSupplierId());
        return dto;
    }

    /**
     * Converts ProductSupplierIdDto to entity
     * @param dto the DTO to convert
     * @return the entity, or null if DTO is null
     */
    public static ProductSupplierId toEntity(ProductSupplierIdDto dto) {
        if (dto == null) {
            return null;
        }
        ProductSupplierId entity = new ProductSupplierId();
        entity.setProductId(dto.getProductId());
        entity.setSupplierId(dto.getSupplierId());
        return entity;
    }
}
