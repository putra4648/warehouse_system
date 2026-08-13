package id.putra.wms.module.warehouse.converter;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.model.entity.ContactPersonWarehouse;

/**
 * Converter for ContactPersonWarehouse entity and DTO.
 * Provides static methods for converting between ContactPersonWarehouse entity and ContactPersonWarehouseDto.
 */
public class ContactPersonWarehouseConverter {

    private ContactPersonWarehouseConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a ContactPersonWarehouse entity to ContactPersonWarehouseDto.
     *
     * @param entity the ContactPersonWarehouse entity
     * @return the ContactPersonWarehouseDto, or null if entity is null
     */
    public static ContactPersonWarehouseDto toDto(ContactPersonWarehouse entity) {
        if (entity == null) {
            return null;
        }
        
        return ContactPersonWarehouseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }

    /**
     * Converts a ContactPersonWarehouseDto to ContactPersonWarehouse entity.
     *
     * @param dto the ContactPersonWarehouseDto
     * @return the ContactPersonWarehouse entity, or null if dto is null
     */
    public static ContactPersonWarehouse toEntity(ContactPersonWarehouseDto dto) {
        if (dto == null) {
            return null;
        }
        
        ContactPersonWarehouse entity = new ContactPersonWarehouse();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        return entity;
    }
}
