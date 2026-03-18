package id.putra.wms.module.outbound.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import id.putra.wms.module.outbound.dto.ShipmentDto;
import id.putra.wms.module.outbound.model.entity.Shipment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { PackingMapper.class, CarrierMapper.class })
public interface ShipmentMapper {
    @Mapping(target = "packing", ignore = true)
    @Mapping(target = "carrier", ignore = true)
    Shipment toEntity(ShipmentDto dto);

    @Mapping(target = "packingId", ignore = true)
    @Mapping(target = "carrierId", ignore = true)
    ShipmentDto toDto(Shipment entity);

    List<ShipmentDto> toDtos(List<Shipment> entities);
}
