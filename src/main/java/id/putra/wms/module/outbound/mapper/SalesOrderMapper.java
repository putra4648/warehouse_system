package id.putra.wms.module.outbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.model.entity.SalesOrder;
import id.putra.wms.shared.base.entity.Customer;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { SalesOrderLineMapper.class })
public interface SalesOrderMapper {

    @Mapping(target = "customer", source = "customerId", qualifiedByName = "customerIdToCustomer")
    SalesOrder toEntity(SalesOrderDto dto);

    @Mapping(target = "customerId", source = "customer", qualifiedByName = "customerToCustomerId")
    SalesOrderDto toDto(SalesOrder entity);

    List<SalesOrderDto> toDtos(List<SalesOrder> entities);

    @Named("customerIdToCustomer")
    default Customer customerIdToCustomer(Long customerId) {
        if (customerId == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
    }

    @Named("customerToCustomerId")
    default Long customerToCustomerId(Customer customer) {
        if (customer == null) {
            return null;
        }
        return customer.getId();
    }

}
