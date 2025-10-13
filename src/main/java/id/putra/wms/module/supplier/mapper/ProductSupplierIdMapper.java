package id.putra.wms.module.supplier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.supplier.dto.ProductSupplierIdDto;
import id.putra.wms.module.supplier.model.entity.ProductSupplierId;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductSupplierIdMapper {
    ProductSupplierIdDto toDto(ProductSupplierId entity);

    ProductSupplierId toEntity(ProductSupplierIdDto dto);
}
