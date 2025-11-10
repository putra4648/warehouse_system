package id.putra.wms.module.outbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.module.outbound.dto.SalesOrderLineDto;
import id.putra.wms.module.outbound.model.entity.SalesOrderLine;
import org.mapstruct.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {InventoryItemMapper.class})
public interface SalesOrderLineMapper {

    @Mapping(target = "salesOrder.id", source = "salesOrderId")
    SalesOrderLine toEntity(SalesOrderLineDto dto);

    @Mapping(target = "salesOrderId", source = "salesOrder.id")
    SalesOrderLineDto toDto(SalesOrderLine entity);

    List<SalesOrderLineDto> toDtos(List<SalesOrderLine> entities);

}
