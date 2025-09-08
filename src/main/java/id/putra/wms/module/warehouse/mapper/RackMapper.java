package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.model.entity.Rack;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { WarehouseMapper.class })
public interface RackMapper {
    RackDto toDto(Rack rack);

    Rack toEntity(RackDto dto);
}
