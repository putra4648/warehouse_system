package id.putra.wms.module.customer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.InjectionStrategy;

import id.putra.wms.module.customer.dto.ContactPersonCustomerDto;
import id.putra.wms.module.customer.model.entity.ContactPersonCustomer;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContactPersonCustomerMapper {
    ContactPersonCustomerDto toDto(ContactPersonCustomer entity);

    ContactPersonCustomer toEntity(ContactPersonCustomerDto dto);

}
