package id.putra.wms.module.inbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.model.entity.PurchaseOrder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseOrderMapper {

    PurchaseOrder toEntity(PurchaseOrderDto dto);

    PurchaseOrderDto toDto(PurchaseOrder entity);

    List<PurchaseOrderDto> toDtos(List<PurchaseOrder> entities);

}
