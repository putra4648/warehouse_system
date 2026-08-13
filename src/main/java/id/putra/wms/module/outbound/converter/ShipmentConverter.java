package id.putra.wms.module.outbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.outbound.dto.ShipmentDto;
import id.putra.wms.module.outbound.model.entity.Shipment;

/**
 * Converter for Shipment entity and DTO.
 * Provides static methods for converting between Shipment entity and ShipmentDto.
 */
public class ShipmentConverter {

    private ShipmentConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Shipment entity to ShipmentDto.
     *
     * @param entity the Shipment entity
     * @return the ShipmentDto, or null if entity is null
     */
    public static ShipmentDto toDto(Shipment entity) {
        if (entity == null) {
            return null;
        }
        
        return ShipmentDto.builder()
                .id(entity.getId())
                .packingId(entity.getPacking() != null ? entity.getPacking().getId() : null)
                .carrierId(entity.getCarrier() != null ? entity.getCarrier().getId() : null)
                .status(entity.getStatus())
                .build();
    }

    /**
     * Converts a Shipment list to ShipmentDto list.
     *
     * @param entities list of Shipment entities
     * @return list of ShipmentDto, or empty list if input is null
     */
    public static List<ShipmentDto> toDtos(List<Shipment> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(ShipmentConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a ShipmentDto to Shipment entity.
     *
     * @param dto the ShipmentDto
     * @return the Shipment entity, or null if dto is null
     */
    public static Shipment toEntity(ShipmentDto dto) {
        if (dto == null) {
            return null;
        }
        
        Shipment entity = new Shipment();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        
        // Set packing defensively
        if (dto.getPackingId() != null) {
            entity.setPacking(new id.putra.wms.module.outbound.model.entity.Packing());
            entity.getPacking().setId(dto.getPackingId());
        }
        
        // Set carrier defensively
        if (dto.getCarrierId() != null) {
            entity.setCarrier(new id.putra.wms.module.outbound.model.entity.Carrier());
            entity.getCarrier().setId(dto.getCarrierId());
        }
        
        return entity;
    }
}
