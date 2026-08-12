package id.putra.wms.module.transaction.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.module.supplier.mapper.ContactPersonSupplierMapper;
import id.putra.wms.module.transaction.dto.TransactionItemDto;
import id.putra.wms.module.transaction.model.entity.TransactionItem;
import id.putra.wms.shared.mapper.ProductMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        ProductMapper.class,
        InventoryItemMapper.class, ContactPersonSupplierMapper.class })
public interface TransactionItemMapper {
    TransactionItemDto toDto(TransactionItem entity);

    TransactionItem toEntity(TransactionItemDto dto);
}
