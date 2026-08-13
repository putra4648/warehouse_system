package id.putra.wms.module.customer.converter;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.shared.base.entity.Customer;

/**
 * Converter for Customer entity and DTO.
 * Provides static methods for converting between Customer entity and CustomerDto.
 */
public class CustomerConverter {

    private CustomerConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Customer entity to CustomerDto.
     *
     * @param entity the Customer entity
     * @return the CustomerDto, or null if entity is null
     */
    public static CustomerDto toDto(Customer entity) {
        if (entity == null) {
            return null;
        }
        
        return CustomerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    /**
     * Converts a CustomerDto to Customer entity.
     *
     * @param dto the CustomerDto
     * @return the Customer entity, or null if dto is null
     */
    public static Customer toEntity(CustomerDto dto) {
        if (dto == null) {
            return null;
        }
        
        Customer entity = Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
        return entity;
    }
}
