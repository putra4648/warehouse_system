package id.putra.wms.module.warehouse.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import id.putra.wms.module.warehouse.dto.form.WarehouseForm;
import id.putra.wms.module.warehouse.dto.response.WarehouseRes;
import id.putra.wms.module.warehouse.model.entity.Warehouse;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    Warehouse toEntity(WarehouseForm req);

    WarehouseRes toResponse(Warehouse warehouse);

    List<WarehouseRes> toResponses(List<Warehouse> warehouses);

}
