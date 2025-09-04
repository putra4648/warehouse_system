package id.putra.wms.module.warehouse.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.WarehouseDto;

public interface WarehouseCoreService {

    Page<WarehouseDto> getWarehouses(WarehouseDto dto, Pageable pageable);

    WarehouseDto getWarehouse(WarehouseDto dto);

    void save(List<WarehouseDto> dtos);

    void update(List<WarehouseDto> dtos);

    void delete(List<WarehouseDto> dtos);

}
