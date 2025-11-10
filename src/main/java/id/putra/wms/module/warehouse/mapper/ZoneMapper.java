package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.model.entity.Zone;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { WarehouseMapper.class , LocationMapper.class})
public interface ZoneMapper {
    ZoneDto toZoneDto(Zone zone);

    Zone toEntity(ZoneDto dto);

}
