package id.putra.wms.module.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.InjectionStrategy;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.model.entity.Rack;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { LocationMapper.class, WarehouseMapper.class,
        ZoneMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RackMapper {

    Rack toEntity(RackDto dto);

    RackDto toDto(Rack rack);

}
