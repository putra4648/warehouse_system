package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactPersonSupplierMapper {
    ContactPersonSupplierMapper INSTANCE = Mappers.getMapper(ContactPersonSupplierMapper.class);
    // ContactPersonSupplierDto toDto(ContactPersonSupplier entity);
    // ContactPersonSupplier toEntity(ContactPersonSupplierDto dto);
}
