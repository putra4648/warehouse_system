package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.model.entity.Rack;

@Mapper(componentModel = "spring")
public interface RackMapper {
    RackDto toRackDto(Rack rack);
}
