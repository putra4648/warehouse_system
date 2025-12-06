package id.putra.wms.module.inbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inbound.dto.ReceivingLineDto;
import id.putra.wms.module.inbound.model.entity.ReceivingLine;
import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.shared.mapper.ProductMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { InventoryItemMapper.class,
        ProductMapper.class })
public interface ReceivingLineMapper {

    ReceivingLine toEntity(ReceivingLineDto dto);

    ReceivingLineDto toDto(ReceivingLine entity);

    List<ReceivingLineDto> toDtos(List<ReceivingLine> entities);

}
