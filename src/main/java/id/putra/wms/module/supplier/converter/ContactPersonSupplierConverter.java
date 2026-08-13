package id.putra.wms.module.supplier.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.supplier.dto.ContactPersonSupplierDto;
import id.putra.wms.module.supplier.model.entity.ContactPersonSupplier;

/**
 * Converter for ContactPersonSupplier entity and DTO.
 * Provides static methods for converting between ContactPersonSupplier entity and ContactPersonSupplierDto.
 */
public class ContactPersonSupplierConverter {

    private ContactPersonSupplierConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a ContactPersonSupplier entity to ContactPersonSupplierDto.
     *
     * @param entity the ContactPersonSupplier entity
     * @return the ContactPersonSupplierDto, or null if entity is null
     */
    public static ContactPersonSupplierDto toDto(ContactPersonSupplier entity) {
        if (entity == null) {
            return null;
        }
        
        return ContactPersonSupplierDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }

    /**
     * Converts a ContactPersonSupplier list to ContactPersonSupplierDto list.
     *
     * @param entities list of ContactPersonSupplier entities
     * @return list of ContactPersonSupplierDto, or empty list if input is null
     */
    public static List<ContactPersonSupplierDto> toDtos(List<ContactPersonSupplier> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(ContactPersonSupplierConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a ContactPersonSupplier list to ContactPersonSupplierDto list.
     *
     * @param entities list of ContactPersonSupplier entities
     * @return list of ContactPersonSupplierDto, or empty list if input is null
     */
    public static List<ContactPersonSupplier> toEntities(List<ContactPersonSupplierDto> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(ContactPersonSupplierConverter::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Converts a ContactPersonSupplierDto to ContactPersonSupplier entity.
     *
     * @param dto the ContactPersonSupplierDto
     * @return the ContactPersonSupplier entity, or null if dto is null
     */
    public static ContactPersonSupplier toEntity(ContactPersonSupplierDto dto) {
        if (dto == null) {
            return null;
        }
        
        ContactPersonSupplier entity = new ContactPersonSupplier();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        return entity;
    }

    /**
     * Updates a ContactPersonSupplier entity with values from ContactPersonSupplierDto.
     * Ignores null values to allow partial updates.
     *
     * @param dto the ContactPersonSupplierDto
     * @param entity the ContactPersonSupplier entity to update
     */
    public static void updateEntityFromDto(ContactPersonSupplierDto dto, ContactPersonSupplier entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            entity.setPhone(dto.getPhone());
        }
    }
}
