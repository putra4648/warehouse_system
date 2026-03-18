package id.putra.wms.module.outbound.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import id.putra.wms.module.outbound.dto.PackingDto;
import id.putra.wms.module.outbound.model.entity.Packing;
import id.putra.wms.module.outbound.model.entity.Shipment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PackingMapper {
    @Mapping(source = "shipmentIds", target = "shipments", qualifiedByName = "mapIdsToShipments")
    Packing toEntity(PackingDto dto);

    @Mapping(source = "shipments", target = "shipmentIds", qualifiedByName = "mapShipmentsToIds")
    PackingDto toDto(Packing entity);

    List<PackingDto> toDtos(List<Packing> entities);

    @Named("mapShipmentsToIds")
    default List<Long> mapShipmentsToIds(List<Shipment> shipments) {
        return shipments.stream().map(Shipment::getId).collect(Collectors.toList());
    }

    @Named("mapIdsToShipments")
    default List<Shipment> mapIdsToShipments(List<Long> shipmentIds) {
        return shipmentIds.stream().map(id -> {
            Shipment shipment = new Shipment();
            shipment.setId(id);
            return shipment;
        }).collect(Collectors.toList());
    }
}
