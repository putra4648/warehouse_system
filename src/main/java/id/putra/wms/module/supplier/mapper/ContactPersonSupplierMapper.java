package id.putra.wms.module.supplier.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import id.putra.wms.module.supplier.dto.ContactPersonSupplierDto;
import id.putra.wms.module.supplier.model.entity.ContactPersonSupplier;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ContactPersonSupplierMapper {

    public abstract ContactPersonSupplierDto toDto(ContactPersonSupplier entity);

    public abstract ContactPersonSupplier toEntity(ContactPersonSupplierDto dto);

    public abstract List<ContactPersonSupplier> toEntities(List<ContactPersonSupplierDto> dtos);

    public abstract void updateEntityFromDto(ContactPersonSupplierDto dto, @MappingTarget ContactPersonSupplier entity);

}
