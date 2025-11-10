package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.model.entity.Rack;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { LocationMapper.class })
public interface RackMapper {

    @Mapping(target = "zone.id", source = "zoneId")
    Rack toEntity(RackDto dto);

    @Mapping(target = "zoneId", source = "zone.id")
    RackDto toDto(Rack rack);

}
