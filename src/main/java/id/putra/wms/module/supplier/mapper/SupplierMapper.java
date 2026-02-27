package id.putra.wms.module.supplier.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.shared.base.entity.Supplier;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        ContactPersonSupplierMapper.class })
public abstract class SupplierMapper {

    public abstract SupplierDto toDto(Supplier entity);

    public abstract List<SupplierDto> toDtos(List<Supplier> entity);

    public abstract Supplier toEntity(SupplierDto dto);

    public abstract void updateEntityFromDto(SupplierDto dto, @MappingTarget Supplier entity);

    @AfterMapping
    protected void setSupplier(@MappingTarget Supplier entity) {
        if (entity.getProducts() != null) {
            entity.getProducts().forEach(p -> p.setSupplier(entity));
        }

    }
}
