package id.putra.wms.module.inbound.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

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

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ReceivingMapper {

    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatusToEnum")
    @Mapping(target = "purchaseOrder", source = "purchaseOrder", qualifiedByName = "mapDtoToPurchaseOrder")
    @Mapping(target = "receivedBy", ignore = true)
    public abstract Receiving toEntity(ReceivingDto dto);

    @Mapping(target = "purchaseOrder", source = "purchaseOrder", qualifiedByName = "mapPurchaseOrderToDto")
    @Mapping(target = "receivingLines", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapEnumToStatus")
    @Mapping(target = "meta", ignore = true)
    public abstract ReceivingDto toDto(Receiving receiving);

    @Mapping(target = "receivingLines", source = "receivingLines", qualifiedByName = "mapLinesToDto")
    @Mapping(target = "meta", ignore = true)
    public abstract ReceivingDto toDtoWithLines(Receiving receiving);

    @Mapping(target = "product", source = "product", qualifiedByName = "mapDtoToProduct")
    @Mapping(target = "location", source = "location", qualifiedByName = "mapDtoToLocation")
    @Mapping(target = "receiving", ignore = true)
    public abstract ReceivingLine toLineEntity(ReceivingLineDto dto);

    @Mapping(target = "product", source = "product", qualifiedByName = "mapProductToDto")
    @Mapping(target = "location", source = "location", qualifiedByName = "mapLocationToDto")
    public abstract ReceivingLineDto toLineDto(ReceivingLine entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatusToEnum")
    @Mapping(target = "purchaseOrder", source = "purchaseOrder", qualifiedByName = "mapDtoToPurchaseOrder")
    public abstract void patchDtoToEntity(ReceivingDto dto, @MappingTarget Receiving entity);

    @AfterMapping
    protected void linkLines(@MappingTarget Receiving receiving) {
        if (receiving.getReceivingLines() != null) {
            receiving.getReceivingLines().forEach(line -> {
                line.setReceiving(receiving);
            });
        }
    }

    @Named("mapPurchaseOrderToId")
    protected Long mapPurchaseOrderToId(PurchaseOrder purchaseOrder) {
        if (purchaseOrder == null) {
            return null;
        }
        return purchaseOrder.getId();
    }

    @Named("mapIdToPurchaseOrder")
    protected PurchaseOrder mapIdToPurchaseOrder(Long id) {
        if (id == null) {
            return null;
        }
        PurchaseOrder entity = new PurchaseOrder();
        entity.setId(id);
        return entity;
    }

    @Named("mapLinesToDto")
    protected List<ReceivingLineDto> mapLinesToDto(List<ReceivingLine> lines) {
        if (lines == null) {
            return null;
        }
        return lines.stream().map(this::toLineDto).collect(Collectors.toList());
    }

    @Named("mapDtoToProduct")
    protected Product mapDtoToProduct(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSku(dto.getSku());
        return entity;
    }

    @Named("mapProductToDto")
    protected ProductDto mapProductToDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto dto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .build();
        return dto;
    }

    @Named("mapStatusToEnum")
    protected OrderStatus mapStatusToEnum(String status) {
        if (status == null) {
            return null;
        }
        return OrderStatus.valueOf(status);
    }

    @Named("mapEnumToStatus")
    protected String mapEnumToStatus(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }

    @Named("mapPurchaseOrderToDto")
    protected PurchaseOrderDto mapPurchaseOrderToDto(PurchaseOrder purchaseOrder) {
        if (purchaseOrder == null) {
            return null;
        }
        PurchaseOrderDto dto = new PurchaseOrderDto();
        dto.setId(purchaseOrder.getId());
        dto.setPoNumber(purchaseOrder.getPoNumber());
        return dto;
    }

    @Named("mapDtoToPurchaseOrder")
    protected PurchaseOrder mapDtoToPurchaseOrder(PurchaseOrderDto dto) {
        if (dto == null) {
            return null;
        }
        PurchaseOrder entity = new PurchaseOrder();
        entity.setId(dto.getId());
        return entity;
    }

    @Named("mapDtoToLocation")
    protected Location mapDtoToLocation(LocationDto dto) {
        if (dto == null) {
            return null;
        }
        Location entity = new Location();
        entity.setId(dto.getId());
        return entity;
    }

    @Named("mapLocationToDto")
    protected LocationDto mapLocationToDto(Location location) {
        if (location == null) {
            return null;
        }
        LocationDto dto = LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .build();
        return dto;
    }
}
