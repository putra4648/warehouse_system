package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductSupplierMapper {
    ProductSupplierMapper INSTANCE = Mappers.getMapper(ProductSupplierMapper.class);
    // ProductSupplierDto toDto(ProductSupplier entity);
    // ProductSupplier toEntity(ProductSupplierDto dto);
}
