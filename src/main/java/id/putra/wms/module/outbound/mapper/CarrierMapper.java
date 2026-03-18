package id.putra.wms.module.outbound.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import id.putra.wms.module.outbound.dto.CarrierDto;
import id.putra.wms.module.outbound.model.entity.Carrier;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarrierMapper {
    @Mapping(target = "shipments", ignore = true)
    Carrier toEntity(CarrierDto dto);

    CarrierDto toDto(Carrier entity);
    List<CarrierDto> toDtos(List<Carrier> entities);
}
