package id.putra.wms.module.customer.converter;

import id.putra.wms.module.customer.dto.ContactPersonCustomerDto;
import id.putra.wms.module.customer.model.entity.ContactPersonCustomer;
import id.putra.wms.shared.base.entity.Customer;

/**
 * Converter for ContactPersonCustomer entity and DTO.
 * Provides static methods for converting between ContactPersonCustomer entity and ContactPersonCustomerDto.
 */
public class ContactPersonCustomerConverter {

    private ContactPersonCustomerConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a ContactPersonCustomer entity to ContactPersonCustomerDto.
     *
     * @param entity the ContactPersonCustomer entity
     * @return the ContactPersonCustomerDto, or null if entity is null
     */
    public static ContactPersonCustomerDto toDto(ContactPersonCustomer entity) {
        if (entity == null) {
            return null;
        }
        
        return ContactPersonCustomerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .customerId(entity.getCustomer() != null ? entity.getCustomer().getId() : null)
                .build();
    }

    /**
     * Converts a ContactPersonCustomerDto to ContactPersonCustomer entity.
     *
     * @param dto the ContactPersonCustomerDto
     * @return the ContactPersonCustomer entity, or null if dto is null
     */
    public static ContactPersonCustomer toEntity(ContactPersonCustomerDto dto) {
        if (dto == null) {
            return null;
        }
        
        ContactPersonCustomer entity = new ContactPersonCustomer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        
        // Set the relationship defensively
        if (dto.getCustomerId() != null) {
            entity.setCustomer(Customer.builder().id(dto.getCustomerId()).build());
        }
        
        return entity;
    }
}
