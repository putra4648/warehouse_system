package id.putra.wms.module.warehouse.mapper;

import id.putra.wms.module.warehouse.dto.request.WarehouseReq;
import id.putra.wms.module.warehouse.dto.response.WarehouseRes;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    Warehouse toEntity(WarehouseReq req);

    WarehouseRes toResponse(Warehouse warehouse);

    List<WarehouseRes> toResponses(List<Warehouse> warehouses);

}
