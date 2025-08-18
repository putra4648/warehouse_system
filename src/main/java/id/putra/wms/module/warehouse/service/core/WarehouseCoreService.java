package id.putra.wms.module.warehouse.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.form.WarehouseForm;
import id.putra.wms.module.warehouse.dto.response.WarehouseRes;

public interface WarehouseCoreService {

    Page<WarehouseRes> getAll(Pageable pageable);

    WarehouseRes getById(String id);

    void save(WarehouseForm req);

}
