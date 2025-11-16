package id.putra.wms.module.supplier.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.shared.base.entity.Supplier;
import id.putra.wms.shared.mapper.ProductMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class, ContactPersonSupplierMapper.class})
public interface SupplierMapper {
    SupplierDto toDto(Supplier entity);

    List<SupplierDto> toDtos(List<Supplier> entity);

    Supplier toEntity(SupplierDto dto);
}
