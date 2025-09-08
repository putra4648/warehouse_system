package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.model.entity.Location;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { WarehouseMapper.class })
public interface LocationMapper {

    LocationDto toDto(Location location);

    Location toEntity(LocationDto dto);

}
