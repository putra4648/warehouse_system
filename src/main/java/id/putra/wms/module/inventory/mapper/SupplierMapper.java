package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);
    // SupplierDto toDto(Supplier entity);
    // Supplier toEntity(SupplierDto dto);
}
