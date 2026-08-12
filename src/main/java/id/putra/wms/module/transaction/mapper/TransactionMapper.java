package id.putra.wms.module.transaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.InjectionStrategy;

import id.putra.wms.module.customer.mapper.ContactPersonCustomerMapper;
import id.putra.wms.module.supplier.mapper.SupplierMapper;
import id.putra.wms.module.transaction.dto.TransactionDto;
import id.putra.wms.module.transaction.model.entity.Transaction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ContactPersonCustomerMapper.class,
        SupplierMapper.class, TransactionItemMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransactionMapper {
    TransactionDto toDto(Transaction entity);

    Transaction toEntity(TransactionDto dto);
}
