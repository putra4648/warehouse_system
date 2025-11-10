package id.putra.wms.module.transaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.module.transaction.dto.TransactionItemDto;
import id.putra.wms.module.transaction.model.entity.TransactionItem;
import id.putra.wms.shared.mapper.ProductMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class, InventoryItemMapper.class})
public interface TransactionItemMapper {
    TransactionItemDto toDto(TransactionItem entity);

    TransactionItem toEntity(TransactionItemDto dto);
}
