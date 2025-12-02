package id.putra.wms.module.warehouse.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.model.entity.Warehouse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WarehouseMapper {

    Warehouse toEntity(WarehouseDto dto);

    WarehouseDto toDto(Warehouse warehouse);

    List<WarehouseDto> toDtos(List<Warehouse> warehouses);

}
