package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.WarehouseDto;

public interface WarehouseQueryAdapter {

    Page<WarehouseDto> getWarehouses(WarehouseDto dto, Pageable pageable);

    WarehouseDto getWarehouseById(WarehouseDto dto);

}
