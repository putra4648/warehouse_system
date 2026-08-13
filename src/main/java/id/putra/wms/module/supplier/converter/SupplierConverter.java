package id.putra.wms.module.supplier.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.shared.base.entity.Supplier;

/**
 * Converter for Supplier entity and DTO.
 * Handles conversion between Supplier entity and SupplierDto with support for related contact persons.
 */
public class SupplierConverter {

    private SupplierConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Supplier entity to SupplierDto.
     *
     * @param entity the Supplier entity
     * @return the SupplierDto, or null if entity is null
     */
    public static SupplierDto toDto(Supplier entity) {
        if (entity == null) {
            return null;
        }
        
        return SupplierDto.builder()
                .id(entity.getId())
                .build();
    }

    /**
     * Converts a Supplier list to SupplierDto list.
     *
     * @param entities list of Supplier entities
     * @return list of SupplierDto, or empty list if input is null
     */
    public static List<SupplierDto> toDtos(List<Supplier> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(SupplierConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a SupplierDto to Supplier entity.
     *
     * @param dto the SupplierDto
     * @return the Supplier entity, or null if dto is null
     */
    public static Supplier toEntity(SupplierDto dto) {
        if (dto == null) {
            return null;
        }
        
        Supplier entity = new Supplier();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        // Initialize products as empty
        entity.setProducts(Collections.emptyList());
        
        return entity;
    }

    /**
     * Updates a Supplier entity with values from SupplierDto.
     * Ignores null values to allow partial updates.
     *
     * @param dto the SupplierDto
     * @param entity the Supplier entity to update
     */
    public static void updateEntityFromDto(SupplierDto dto, Supplier entity) {
        if (dto == null || entity == null) {
            return;
        }
        
      
    }

    /**
     * Links products to their parent supplier.
     *
     * @param entity the Supplier entity
     */
    public static void setSupplier(Supplier entity) {
        if (entity != null && entity.getProducts() != null) {
            entity.getProducts().forEach(p -> p.setSupplier(entity));
        }
    }
}
