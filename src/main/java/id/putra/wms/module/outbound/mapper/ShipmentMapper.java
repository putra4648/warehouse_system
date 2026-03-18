package id.putra.wms.module.outbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.outbound.dto.ShipmentDto;
import id.putra.wms.module.outbound.model.entity.Shipment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { PackingMapper.class, CarrierMapper.class })
public interface ShipmentMapper {
    @Mapping(source = "packingId", target = "packing.id")
    @Mapping(source = "carrierId", target = "carrier.id")
    Shipment toEntity(ShipmentDto dto);

    @Mapping(source = "packing.id", target = "packingId")
    @Mapping(source = "carrier.id", target = "carrierId")
    ShipmentDto toDto(Shipment entity);

    List<ShipmentDto> toDtos(List<Shipment> entities);
}
