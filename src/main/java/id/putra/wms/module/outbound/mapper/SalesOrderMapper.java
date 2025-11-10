package id.putra.wms.module.outbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.model.entity.SalesOrder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {SalesOrderLineMapper.class})
public interface SalesOrderMapper {

    SalesOrder toEntity(SalesOrderDto dto);

    SalesOrderDto toDto(SalesOrder entity);

    List<SalesOrderDto> toDtos(List<SalesOrder> entities);

}
