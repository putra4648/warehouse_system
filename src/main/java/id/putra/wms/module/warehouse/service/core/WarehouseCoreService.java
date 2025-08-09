package id.putra.wms.module.warehouse.service.core;

import id.putra.wms.module.warehouse.dto.request.WarehouseReq;
import id.putra.wms.module.warehouse.dto.response.WarehouseRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseCoreService {

    Page<WarehouseRes> getAll(Pageable pageable);

    WarehouseRes getById(String id);

    void save(WarehouseReq req);

}
