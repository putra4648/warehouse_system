package id.putra.wms.module.outbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.module.outbound.dto.SalesOrderLineDto;
import id.putra.wms.module.outbound.model.entity.SalesOrderLine;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {InventoryItemMapper.class})
public interface SalesOrderLineMapper {

    SalesOrderLine toEntity(SalesOrderLineDto dto);

    SalesOrderLineDto toDto(SalesOrderLine entity);

    List<SalesOrderLineDto> toDtos(List<SalesOrderLine> entities);

}
