package id.putra.wms.module.warehouse.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.model.entity.Location;
import id.putra.wms.module.warehouse.model.entity.Rack;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.module.warehouse.model.entity.Zone;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    Warehouse toWarehouseEntity(WarehouseDto dto);

    WarehouseDto toWarehouseDto(Warehouse warehouse);

    List<WarehouseDto> toWarehousDtos(List<Warehouse> warehouses);

    ZoneDto toZoneDto(Zone zone);

    RackDto toRackDto(Rack rack);

    LocationDto toLocationDto(Location location);

}
