package id.putra.wms.module.inbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.dto.PurchaseOrderLineDto;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import id.putra.wms.module.inbound.model.entity.PurchaseOrderLine;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.base.entity.Product;
import id.putra.wms.shared.base.entity.Supplier;

/**
 * Converter for PurchaseOrder entity and DTO.
 * Handles conversion between PurchaseOrder entity and PurchaseOrderDto with support for related lines.
 */
public class PurchaseOrderConverter {

    private PurchaseOrderConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a PurchaseOrder entity to PurchaseOrderDto.
     *
     * @param entity the PurchaseOrder entity
     * @return the PurchaseOrderDto, or null if entity is null
     */
    public static PurchaseOrderDto toDto(PurchaseOrder entity) {
        if (entity == null) {
            return null;
        }
        
        return PurchaseOrderDto.builder()
                .id(entity.getId())
                .supplierId(entity.getSupplier() != null ? entity.getSupplier().getId() : null)
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus())
                .purchaseOrderLines(Collections.emptyList()) // Lines ignored by default
                .build();
    }

    /**
     * Converts a PurchaseOrder entity to PurchaseOrderDto including related lines.
     *
     * @param entity the PurchaseOrder entity
     * @return the PurchaseOrderDto with lines, or null if entity is null
     */
    public static PurchaseOrderDto toDtoWithLines(PurchaseOrder entity) {
        if (entity == null) {
            return null;
        }
        
        List<PurchaseOrderLineDto> lines = entity.getPurchaseOrderLines() != null 
            ? entity.getPurchaseOrderLines().stream()
                    .map(PurchaseOrderConverter::toLineDto)
                    .collect(Collectors.toList())
            : Collections.emptyList();
        
        return PurchaseOrderDto.builder()
                .id(entity.getId())
                .supplierId(entity.getSupplier() != null ? entity.getSupplier().getId() : null)
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus())
                .purchaseOrderLines(lines)
                .build();
    }

    /**
     * Converts a PurchaseOrderDto to PurchaseOrder entity.
     *
     * @param dto the PurchaseOrderDto
     * @return the PurchaseOrder entity, or null if dto is null
     */
    public static PurchaseOrder toEntity(PurchaseOrderDto dto) {
        if (dto == null) {
            return null;
        }
        
        PurchaseOrder entity = new PurchaseOrder();
        entity.setId(dto.getId());
        entity.setOrderDate(dto.getOrderDate());
        entity.setStatus(dto.getStatus());
        
        // Set supplier defensively
        if (dto.getSupplierId() != null) {
            Supplier supplier = new Supplier();
            supplier.setId(dto.getSupplierId());
            entity.setSupplier(supplier);
        }
        
        // Initialize receivings as empty
        entity.setReceivings(Collections.emptyList());
        
        return entity;
    }

    /**
     * Converts a PurchaseOrderLineDto to PurchaseOrderLine entity.
     *
     * @param dto the PurchaseOrderLineDto
     * @return the PurchaseOrderLine entity, or null if dto is null
     */
    public static PurchaseOrderLine toLineEntity(PurchaseOrderLineDto dto) {
        if (dto == null) {
            return null;
        }
        
        PurchaseOrderLine entity = new PurchaseOrderLine();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        
        // Set product defensively
        if (dto.getProduct() != null) {
            Product product = new Product();
            product.setId(dto.getProduct().getId());
            product.setName(dto.getProduct().getName());
            product.setSku(dto.getProduct().getSku());
            entity.setProduct(product);
        }
        
        return entity;
    }

    /**
     * Converts a PurchaseOrderLine entity to PurchaseOrderLineDto.
     *
     * @param entity the PurchaseOrderLine entity
     * @return the PurchaseOrderLineDto, or null if entity is null
     */
    public static PurchaseOrderLineDto toLineDto(PurchaseOrderLine entity) {
        if (entity == null) {
            return null;
        }
        
        ProductDto productDto = null;
        if (entity.getProduct() != null) {
            productDto = ProductDto.builder()
                    .id(entity.getProduct().getId())
                    .name(entity.getProduct().getName())
                    .sku(entity.getProduct().getSku())
                    .build();
        }
        
        return PurchaseOrderLineDto.builder()
                .id(entity.getId())
                .product(productDto)
                .build();
    }

    /**
     * Patches a PurchaseOrder entity with values from PurchaseOrderDto.
     * Ignores null values to allow partial updates.
     *
     * @param dto the PurchaseOrderDto
     * @param entity the PurchaseOrder entity to update
     */
    public static void patchDtoToEntity(PurchaseOrderDto dto, PurchaseOrder entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        if (dto.getOrderDate() != null) {
            entity.setOrderDate(dto.getOrderDate());
        }
       
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
       
        if (dto.getSupplierId() != null) {
            Supplier supplier = new Supplier();
            supplier.setId(dto.getSupplierId());
            entity.setSupplier(supplier);
        }
    }
}
