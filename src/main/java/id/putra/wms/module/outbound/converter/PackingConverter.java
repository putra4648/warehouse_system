package id.putra.wms.module.outbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.outbound.dto.PackingDto;
import id.putra.wms.module.outbound.model.entity.Packing;
import id.putra.wms.module.outbound.model.entity.Shipment;

/**
 * Converter for Packing entity and DTO.
 * Provides static methods for converting between Packing entity and PackingDto.
 */
public class PackingConverter {

    private PackingConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Packing entity to PackingDto.
     *
     * @param entity the Packing entity
     * @return the PackingDto, or null if entity is null
     */
    public static PackingDto toDto(Packing entity) {
        if (entity == null) {
            return null;
        }
        
        List<Long> shipmentIds = mapShipmentsToIds(entity.getShipments());
        
        return PackingDto.builder()
                .id(entity.getId())
                .shipmentIds(shipmentIds)
                .build();
    }

    /**
     * Converts a Packing list to PackingDto list.
     *
     * @param entities list of Packing entities
     * @return list of PackingDto, or empty list if input is null
     */
    public static List<PackingDto> toDtos(List<Packing> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(PackingConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a PackingDto to Packing entity.
     *
     * @param dto the PackingDto
     * @return the Packing entity, or null if dto is null
     */
    public static Packing toEntity(PackingDto dto) {
        if (dto == null) {
            return null;
        }
        
        Packing entity = new Packing();
        entity.setId(dto.getId());
        
        // Set shipments defensively
        List<Shipment> shipments = mapIdsToShipments(dto.getShipmentIds());
        entity.setShipments(shipments);
        
        return entity;
    }

    /**
     * Converts a list of Shipment entities to their IDs.
     *
     * @param shipments the list of Shipment entities
     * @return list of IDs, or empty list if input is null
     */
    private static List<Long> mapShipmentsToIds(List<Shipment> shipments) {
        if (shipments == null) {
            return Collections.emptyList();
        }
        return shipments.stream()
                .map(s -> s.getId())
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of IDs to Shipment entities.
     *
     * @param shipmentIds the list of Shipment IDs
     * @return list of Shipment entities, or empty list if input is null
     */
    private static List<Shipment> mapIdsToShipments(List<Long> shipmentIds) {
        if (shipmentIds == null) {
            return Collections.emptyList();
        }
        return shipmentIds.stream()
                .map(id -> {
                    Shipment shipment = new Shipment();
                    shipment.setId(id);
                    return shipment;
                })
                .collect(Collectors.toList());
    }
}
