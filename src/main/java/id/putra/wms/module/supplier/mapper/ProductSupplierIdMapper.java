package id.putra.wms.module.supplier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.InjectionStrategy;

import id.putra.wms.module.supplier.dto.ProductSupplierIdDto;
import id.putra.wms.module.supplier.model.entity.ProductSupplierId;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductSupplierIdMapper {
    ProductSupplierIdDto toDto(ProductSupplierId entity);

    ProductSupplierId toEntity(ProductSupplierIdDto dto);
}
