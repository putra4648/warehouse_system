package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.model.entity.Zone;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ZoneMapper {
    @Mapping(target = "warehouseId", source = "warehouse.id")
    ZoneDto toDto(Zone entity);

    @Mapping(target = "warehouse.id", source = "warehouseId")
    Zone toEntity(ZoneDto dto);

}
