package id.putra.wms.module.inbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.model.entity.Receiving;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ReceivingLineMapper.class })
public interface ReceivingMapper {

    Receiving toEntity(ReceivingDto dto);

    ReceivingDto toDto(Receiving receiving);

    List<ReceivingDto> toDtos(List<Receiving> entities);

}
