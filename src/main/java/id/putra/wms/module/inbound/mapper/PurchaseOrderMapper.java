package id.putra.wms.module.inbound.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.dto.PurchaseOrderLineDto;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import id.putra.wms.module.inbound.model.entity.PurchaseOrderLine;
import id.putra.wms.module.supplier.mapper.SupplierMapper;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.base.entity.Product;
import id.putra.wms.shared.base.entity.Supplier;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        SupplierMapper.class })
public abstract class PurchaseOrderMapper {

    @Mapping(target = "supplier", source = "supplierId", qualifiedByName = "mapIdToSupplier")
    public abstract PurchaseOrder toEntity(PurchaseOrderDto dto);

    @Mapping(target = "supplierId", source = "supplier", qualifiedByName = "mapSupplierToId")
    @Mapping(target = "purchaseOrderLines", ignore = true)
    public abstract PurchaseOrderDto toDto(PurchaseOrder entity);

    @Mapping(target = "supplierId", source = "supplier", qualifiedByName = "mapSupplierToId")
    @Mapping(target = "purchaseOrderLines", source = "purchaseOrderLines", qualifiedByName = "mapLinesToDto")
    public abstract PurchaseOrderDto toDtoWithLines(PurchaseOrder entity);

    @Mapping(target = "product", source = "product", qualifiedByName = "mapDtoToProduct")
    public abstract PurchaseOrderLine toLineEntity(PurchaseOrderLineDto dto);

    @Mapping(target = "product", source = "product", qualifiedByName = "mapProductToDto")
    public abstract PurchaseOrderLineDto toLineDto(PurchaseOrderLine entity);

    @AfterMapping
    protected void linkLines(@MappingTarget PurchaseOrder purchaseOrder) {
        if (purchaseOrder.getPurchaseOrderLines() != null) {
            purchaseOrder.getPurchaseOrderLines().forEach(line -> {
                line.setPurchaseOrder(purchaseOrder);
            });
        }
    }

    @Named("mapLinesToDto")
    protected List<PurchaseOrderLineDto> mapLinesToDto(List<PurchaseOrderLine> lines) {
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

    @Named("mapSupplierToId")
    protected Long mapSupplierToId(Supplier supplier) {
        if (supplier == null) {
            return null;
        }
        return supplier.getId();
    }

    @Named("mapIdToSupplier")
    protected Supplier mapIdToSupplier(Long id) {
        if (id == null) {
            return null;
        }
        Supplier entity = new Supplier();
        entity.setId(id);
        return entity;
    }

}
