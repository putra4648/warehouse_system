package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.model.entity.Zone;

@Mapper(componentModel = "spring", uses = { WarehouseMapper.class })
public interface ZoneMapper {
    ZoneDto toZoneDto(Zone zone);

    Zone toEntity(ZoneDto dto);

}
