package id.putra.wms.module.transaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.customer.model.entity.ContactPersonCustomer;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ContactPersonCustomer.class })
public interface TransactionMapper {
    // TransactionDto toDto(Transaction entity);

    // Transaction toEntity(TransactionDto dto);
}
