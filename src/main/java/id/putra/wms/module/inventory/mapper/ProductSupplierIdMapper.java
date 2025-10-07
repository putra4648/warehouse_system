package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductSupplierIdMapper {
    ProductSupplierIdMapper INSTANCE = Mappers.getMapper(ProductSupplierIdMapper.class);
    // ProductSupplierIdDto toDto(ProductSupplierId entity);
    // ProductSupplierId toEntity(ProductSupplierIdDto dto);
}
