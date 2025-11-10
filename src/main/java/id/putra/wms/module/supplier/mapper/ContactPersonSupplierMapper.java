package id.putra.wms.module.supplier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.supplier.dto.ContactPersonSupplierDto;
import id.putra.wms.module.supplier.model.entity.ContactPersonSupplier;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactPersonSupplierMapper {

    @Mapping(target = "supplierId", source = "supplier.id")
    ContactPersonSupplierDto toDto(ContactPersonSupplier entity);

    @Mapping(target = "supplier.id", source = "supplierId")
    ContactPersonSupplier toEntity(ContactPersonSupplierDto dto);

}
