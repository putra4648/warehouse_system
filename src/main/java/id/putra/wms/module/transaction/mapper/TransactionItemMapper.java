package id.putra.wms.module.transaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.transaction.dto.TransactionItemDto;
import id.putra.wms.module.transaction.model.entity.TransactionItem;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionItemMapper {
    TransactionItemDto toDto(TransactionItem entity);

    TransactionItem toEntity(TransactionItemDto dto);
}
