package id.putra.wms.module.transaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionItemMapper {
    TransactionItemMapper INSTANCE = Mappers.getMapper(TransactionItemMapper.class);
    // TransactionItemDto toDto(TransactionItem entity);
    // TransactionItem toEntity(TransactionItemDto dto);
}
