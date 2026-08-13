package id.putra.wms.module.supplier.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.inventory.model.repository.ProductRepository;
import id.putra.wms.module.supplier.dto.ProductSupplierDto;
import id.putra.wms.module.supplier.model.entity.ProductSupplier;
import id.putra.wms.module.supplier.model.entity.ProductSupplierId;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.base.entity.Product;

/**
 * Converter for ProductSupplier entity and DTO.
 * Handles conversion between ProductSupplier entity and ProductSupplierDto with
 * product initialization.
 * NOTE: This converter requires manual injection of ProductRepository for
 * product lookup.
 */
public class ProductSupplierConverter {

    private ProductSupplierConverter() {
        // Private constructor to prevent instantiation
    }

    // Static reference to ProductRepository - should be set via setter or
    // constructor
    private static ProductRepository productRepository;

    /**
     * Sets the ProductRepository for product lookups.
     * This should be called during application initialization.
     *
     * @param repository the ProductRepository instance
     */
    public static void setProductRepository(ProductRepository repository) {
        productRepository = repository;
    }

    /**
     * Converts a ProductSupplier entity to ProductSupplierDto.
     *
     * @param entity the ProductSupplier entity
     * @return the ProductSupplierDto, or null if entity is null
     */
    public static ProductSupplierDto toDto(ProductSupplier entity) {
        if (entity == null) {
            return null;
        }

        return ProductSupplierDto.builder()
                .id(ProductSupplierIdConverter.toDto(entity.getId()))
                .product(entity.getProduct() != null ? ProductDto.builder().id(entity.getProduct().getId()).build()
                        : null)
                .build();
    }

    /**
     * Converts a ProductSupplier list to ProductSupplierDto list.
     *
     * @param entities list of ProductSupplier entities
     * @return list of ProductSupplierDto, or empty list if input is null
     */
    public static List<ProductSupplierDto> toDtos(List<ProductSupplier> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(ProductSupplierConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a ProductSupplierDto to ProductSupplier entity.
     *
     * @param dto the ProductSupplierDto
     * @return the ProductSupplier entity, or null if dto is null
     */
    public static ProductSupplier toEntity(ProductSupplierDto dto) {
        if (dto == null) {
            return null;
        }

        ProductSupplier entity = new ProductSupplier();
        if (dto.getId() != null) {
            entity.setId(ProductSupplierIdConverter.toEntity(dto.getId()));
        }
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        return entity;
    }

    /**
     * Converts a ProductSupplier list to ProductSupplier entity list.
     *
     * @param dtos list of ProductSupplierDto
     * @return list of ProductSupplier entities, or empty list if input is null
     */
    public static List<ProductSupplier> toEntities(List<ProductSupplierDto> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(ProductSupplierConverter::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Patches a ProductSupplier entity with values from ProductSupplierDto.
     * Ignores null values and the product field to allow partial updates.
     *
     * @param dto    the ProductSupplierDto
     * @param entity the ProductSupplier entity to update
     */
    public static void updateEntityFromDto(ProductSupplierDto dto, ProductSupplier entity) {
        if (dto == null || entity == null) {
            return;
        }
    }

    /**
     * Initializes ProductSupplier ID and product reference after mapping.
     * This should be called after conversion if product needs to be persisted.
     *
     * @param entity the ProductSupplier entity to initialize
     * @param dto    the source ProductSupplierDto
     */
    public static void initIdAndProduct(ProductSupplier entity, ProductSupplierDto dto) {
        if (entity == null) {
            return;
        }

        // Initialize ID if not set
        if (entity.getId() == null) {
            entity.setId(new ProductSupplierId());
        }

        // Set product reference if needed and repository is available
        if (dto != null && dto.getProduct() != null && dto.getProduct().getId() != null) {
            entity.getId().setProductId(dto.getProduct().getId());
            if (productRepository != null) {
                try {
                    entity.setProduct(productRepository.getReferenceById(dto.getProduct().getId()));
                } catch (Exception e) {
                    // Product not found - leave as null and set defensively
                    Product product = new Product();
                    product.setId(dto.getProduct().getId());
                    entity.setProduct(product);
                }
            }
        }
    }
}
