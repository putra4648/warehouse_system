package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.model.entity.Rack;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { LocationMapper.class, ZoneMapper.class })
public interface RackMapper {

    Rack toEntity(RackDto dto);

    RackDto toDto(Rack rack);

}
