package id.putra.wms.module.supplier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.module.supplier.model.entity.Supplier;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplierMapper {
    SupplierDto toDto(Supplier entity);

    Supplier toEntity(SupplierDto dto);
}
