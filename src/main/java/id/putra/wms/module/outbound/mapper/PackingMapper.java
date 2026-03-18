package id.putra.wms.module.outbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.outbound.dto.PackingDto;
import id.putra.wms.module.outbound.model.entity.Packing;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PackingMapper {
    @Mapping(target = "shipments", ignore = true)
    Packing toEntity(PackingDto dto);

    @Mapping(target = "shipmentIds", ignore = true)
    PackingDto toDto(Packing entity);

    List<PackingDto> toDtos(List<Packing> entities);
}
