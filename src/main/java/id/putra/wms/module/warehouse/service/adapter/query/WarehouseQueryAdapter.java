package id.putra.wms.module.warehouse.service.adapter.query;

import id.putra.wms.module.warehouse.dto.response.WarehouseRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WarehouseQueryAdapter {

    Page<WarehouseRes> getAll(Pageable pageable);

    Optional<WarehouseRes> getById(String id);

}
