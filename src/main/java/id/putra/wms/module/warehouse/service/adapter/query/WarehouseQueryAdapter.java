package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.WarehouseDto;

import java.util.Optional;

public interface WarehouseQueryAdapter {

    Page<WarehouseDto> getAll(Pageable pageable);

    Optional<WarehouseDto> getById(String id);

}
