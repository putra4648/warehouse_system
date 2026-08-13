package id.putra.wms.module.inbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.dto.ReceivingLineDto;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import id.putra.wms.module.inbound.model.entity.Receiving;
import id.putra.wms.module.inbound.model.entity.ReceivingLine;
import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.model.entity.Location;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.base.entity.Product;
import id.putra.wms.shared.enums.OrderStatus;

/**
 * Converter for Receiving entity and DTO.
 * Handles conversion between Receiving entity and ReceivingDto with support for related lines, products, and locations.
 */
public class ReceivingConverter {

    private ReceivingConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Receiving entity to ReceivingDto.
     *
     * @param entity the Receiving entity
     * @return the ReceivingDto, or null if entity is null
     */
    public static ReceivingDto toDto(Receiving entity) {
        if (entity == null) {
            return null;
        }
        
        PurchaseOrderDto purchaseOrderDto = null;
        if (entity.getPurchaseOrder() != null) {
            purchaseOrderDto = PurchaseOrderDto.builder()
                    .id(entity.getPurchaseOrder().getId())
                    .build();
        }
        
        return ReceivingDto.builder()
                .id(entity.getId())
                .purchaseOrder(purchaseOrderDto)
                .receivedDate(entity.getReceivedDate())
                .status(mapEnumToStatus(entity.getStatus()))
                .receivingLines(Collections.emptyList()) // Lines ignored by default
                .build();
    }

    /**
     * Converts a Receiving entity to ReceivingDto including related lines.
     *
     * @param entity the Receiving entity
     * @return the ReceivingDto with lines, or null if entity is null
     */
    public static ReceivingDto toDtoWithLines(Receiving entity) {
        if (entity == null) {
            return null;
        }
        
        List<ReceivingLineDto> lines = entity.getReceivingLines() != null 
            ? entity.getReceivingLines().stream()
                    .map(ReceivingConverter::toLineDto)
                    .collect(Collectors.toList())
            : Collections.emptyList();
        
        PurchaseOrderDto purchaseOrderDto = null;
        if (entity.getPurchaseOrder() != null) {
            purchaseOrderDto = PurchaseOrderDto.builder()
                    .id(entity.getPurchaseOrder().getId())
                    .build();
        }
        
        return ReceivingDto.builder()
                .id(entity.getId())
                .purchaseOrder(purchaseOrderDto)
                .receivedDate(entity.getReceivedDate())
                .status(mapEnumToStatus(entity.getStatus()))
                .receivingLines(lines)
                .build();
    }

    /**
     * Converts a ReceivingDto to Receiving entity.
     *
     * @param dto the ReceivingDto
     * @return the Receiving entity, or null if dto is null
     */
    public static Receiving toEntity(ReceivingDto dto) {
        if (dto == null) {
            return null;
        }
        
        Receiving entity = new Receiving();
        entity.setId(dto.getId());
        entity.setReceivedDate(dto.getReceivedDate());
        entity.setStatus(mapStatusToEnum(dto.getStatus()));
        
        // Set purchase order defensively
        if (dto.getPurchaseOrder() != null && dto.getPurchaseOrder().getId() != null) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setId(dto.getPurchaseOrder().getId());
            entity.setPurchaseOrder(purchaseOrder);
        }
        
        return entity;
    }

    /**
     * Converts a ReceivingLineDto to ReceivingLine entity.
     *
     * @param dto the ReceivingLineDto
     * @return the ReceivingLine entity, or null if dto is null
     */
    public static ReceivingLine toLineEntity(ReceivingLineDto dto) {
        if (dto == null) {
            return null;
        }
        
        ReceivingLine entity = new ReceivingLine();
        entity.setId(dto.getId());
        
        // Set product defensively
        if (dto.getProduct() != null) {
            Product product = new Product();
            product.setId(dto.getProduct().getId());
            product.setName(dto.getProduct().getName());
            product.setSku(dto.getProduct().getSku());
            entity.setProduct(product);
        }
        
        // Set location defensively
        if (dto.getLocation() != null) {
            Location location = new Location();
            location.setId(dto.getLocation().getId());
            entity.setLocation(location);
        }
        
        return entity;
    }

    /**
     * Converts a ReceivingLine entity to ReceivingLineDto.
     *
     * @param entity the ReceivingLine entity
     * @return the ReceivingLineDto, or null if entity is null
     */
    public static ReceivingLineDto toLineDto(ReceivingLine entity) {
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
        
        LocationDto locationDto = null;
        if (entity.getLocation() != null) {
            locationDto = LocationDto.builder()
                    .id(entity.getLocation().getId())
                    .build();
        }
        
        return ReceivingLineDto.builder()
                .id(entity.getId())
                .product(productDto)
                .location(locationDto)
                .build();
    }

    /**
     * Patches a Receiving entity with values from ReceivingDto.
     * Ignores null values to allow partial updates.
     *
     * @param dto the ReceivingDto
     * @param entity the Receiving entity to update
     */
    public static void patchDtoToEntity(ReceivingDto dto, Receiving entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        if (dto.getReceivedDate() != null) {
            entity.setReceivedDate(dto.getReceivedDate());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(mapStatusToEnum(dto.getStatus()));
        }
      
        if (dto.getPurchaseOrder() != null && dto.getPurchaseOrder().getId() != null) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setId(dto.getPurchaseOrder().getId());
            entity.setPurchaseOrder(purchaseOrder);
        }
    }

    /**
     * Links receiving lines to their parent receiving order.
     *
     * @param receiving the Receiving entity
     */
    public static void linkLines(Receiving receiving) {
        if (receiving != null && receiving.getReceivingLines() != null) {
            receiving.getReceivingLines().forEach(line -> {
                line.setReceiving(receiving);
            });
        }
    }

    /**
     * Maps OrderStatus enum to String status.
     *
     * @param status the OrderStatus enum
     * @return the String status, or null if status is null
     */
    private static String mapEnumToStatus(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return status.toString();
    }

    /**
     * Maps String status to OrderStatus enum.
     *
     * @param status the String status
     * @return the OrderStatus enum, or null if status is null
     */
    private static OrderStatus mapStatusToEnum(String status) {
        if (status == null) {
            return null;
        }
        return OrderStatus.valueOf(status);
    }
}
