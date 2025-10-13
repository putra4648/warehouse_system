package id.putra.wms.module.supplier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.supplier.dto.ProductSupplierDto;
import id.putra.wms.module.supplier.model.entity.ProductSupplier;
import id.putra.wms.shared.mapper.ProductMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ProductMapper.class, SupplierMapper.class })
public interface ProductSupplierMapper {
    ProductSupplierDto toDto(ProductSupplier entity);

    ProductSupplier toEntity(ProductSupplierDto dto);
}
