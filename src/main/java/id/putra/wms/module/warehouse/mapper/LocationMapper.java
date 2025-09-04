package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.model.entity.Location;

@Mapper(componentModel = "spring", uses = { WarehouseMapper.class })
public interface LocationMapper {

    LocationDto toDto(Location location);

    Location toEntity(LocationDto dto);

}
