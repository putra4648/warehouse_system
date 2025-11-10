package id.putra.wms.module.supplier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.supplier.dto.ContactPersonSupplierDto;
import id.putra.wms.module.supplier.model.entity.ContactPersonSupplier;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {SupplierMapper.class})
public interface ContactPersonSupplierMapper {
    ContactPersonSupplierDto toDto(ContactPersonSupplier entity);

    ContactPersonSupplier toEntity(ContactPersonSupplierDto dto);
}
