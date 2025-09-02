package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.model.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toLocationDto(Location location);

}
