package id.putra.wms.module.outbound.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.model.entity.SalesOrder;
import id.putra.wms.shared.base.entity.Customer;

/**
 * Converter for SalesOrder entity and DTO.
 * Handles conversion between SalesOrder entity and SalesOrderDto with custom ID mappings for customer.
 */
public class SalesOrderConverter {

    private SalesOrderConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a SalesOrder entity to SalesOrderDto.
     *
     * @param entity the SalesOrder entity
     * @return the SalesOrderDto, or null if entity is null
     */
    public static SalesOrderDto toDto(SalesOrder entity) {
        if (entity == null) {
            return null;
        }
        
        return SalesOrderDto.builder()
                .id(entity.getId())
                .customerId(customerToCustomerId(entity.getCustomer()))
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus())
                .salesOrderLines(Collections.emptyList()) // Lines ignored by default
                .build();
    }

    /**
     * Converts a SalesOrder list to SalesOrderDto list.
     *
     * @param entities list of SalesOrder entities
     * @return list of SalesOrderDto, or empty list if input is null
     */
    public static List<SalesOrderDto> toDtos(List<SalesOrder> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(SalesOrderConverter::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a SalesOrderDto to SalesOrder entity.
     *
     * @param dto the SalesOrderDto
     * @return the SalesOrder entity, or null if dto is null
     */
    public static SalesOrder toEntity(SalesOrderDto dto) {
        if (dto == null) {
            return null;
        }
        
        SalesOrder entity = new SalesOrder();
        entity.setId(dto.getId());
        entity.setCustomer(customerIdToCustomer(dto.getCustomerId()));
        entity.setOrderDate(dto.getOrderDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    /**
     * Maps a customer ID to a Customer entity.
     *
     * @param customerId the customer ID
     * @return Customer entity with ID set, or null if customerId is null
     */
    private static Customer customerIdToCustomer(Long customerId) {
        if (customerId == null) {
            return null;
        }
        Customer customer = Customer.builder()
                .id(customerId)
                .build();
        return customer;
    }

    /**
     * Maps a Customer entity to its ID.
     *
     * @param customer the Customer entity
     * @return the customer ID, or null if customer is null
     */
    private static Long customerToCustomerId(Customer customer) {
        if (customer == null) {
            return null;
        }
        return customer.getId();
    }
}
