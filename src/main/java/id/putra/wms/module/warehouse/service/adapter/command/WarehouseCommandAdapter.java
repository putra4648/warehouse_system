package id.putra.wms.module.warehouse.service.adapter.command;

import java.util.List;

import id.putra.wms.module.warehouse.dto.WarehouseDto;

public interface WarehouseCommandAdapter {

    void save(List<WarehouseDto> dtos);

    void update(List<WarehouseDto> dtos);

    void delete(List<WarehouseDto> dtos);

}
