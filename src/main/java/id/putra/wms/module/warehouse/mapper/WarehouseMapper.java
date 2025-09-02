package id.putra.wms.module.warehouse.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.model.entity.Warehouse;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    Warehouse toWarehouseEntity(WarehouseDto dto);

    WarehouseDto toWarehouseDto(Warehouse warehouse);

    List<WarehouseDto> toWarehousDtos(List<Warehouse> warehouses);

}
