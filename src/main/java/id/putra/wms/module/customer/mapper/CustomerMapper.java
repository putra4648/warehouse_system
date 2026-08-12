package id.putra.wms.module.customer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.InjectionStrategy;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.shared.base.entity.Customer;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {
    CustomerDto toDto(Customer entity);

    Customer toEntity(CustomerDto dto);
}
