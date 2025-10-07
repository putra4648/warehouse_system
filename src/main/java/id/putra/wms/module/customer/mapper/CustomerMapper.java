package id.putra.wms.module.customer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.customer.model.entity.Customer;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    CustomerDto toDto(Customer entity);

    Customer toEntity(CustomerDto dto);
}
