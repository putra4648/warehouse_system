package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.model.entity.ContactPersonWarehouse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { WarehouseMapper.class })
public interface ContactPersonWarehouseMapper {

    ContactPersonWarehouseDto toDto(ContactPersonWarehouse entity);

    ContactPersonWarehouse toEntity(ContactPersonWarehouseDto dto);

}
