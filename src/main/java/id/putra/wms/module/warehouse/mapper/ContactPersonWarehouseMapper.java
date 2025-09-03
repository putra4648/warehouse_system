package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.model.entity.ContactPersonWarehouse;

@Mapper(componentModel = "spring", uses = { WarehouseMapper.class })
public interface ContactPersonWarehouseMapper {

    ContactPersonWarehouseDto toDto(ContactPersonWarehouse entity);

    ContactPersonWarehouse toEntity(ContactPersonWarehouseDto dto);

}
