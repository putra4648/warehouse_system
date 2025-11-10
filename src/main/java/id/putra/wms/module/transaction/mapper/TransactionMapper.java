package id.putra.wms.module.transaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.customer.model.entity.ContactPersonCustomer;
import id.putra.wms.module.supplier.mapper.SupplierMapper;
import id.putra.wms.module.transaction.dto.TransactionDto;
import id.putra.wms.module.transaction.model.entity.Transaction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ContactPersonCustomer.class, SupplierMapper.class, TransactionItemMapper.class })
public interface TransactionMapper {
    TransactionDto toDto(Transaction entity);

    Transaction toEntity(TransactionDto dto);
}
