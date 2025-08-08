package id.putra.wms.module.warehouse.service.core.impl;

import id.putra.wms.module.warehouse.dto.request.WarehouseReq;
import id.putra.wms.module.warehouse.dto.response.WarehouseRes;
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import id.putra.wms.shared.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseCoreServiceImpl implements WarehouseCoreService {

    private final WarehouseQueryAdapter warehouseQueryAdapter;

    private final WarehouseCommandAdapter warehouseCommandAdapter;

    @Override
    public Page<WarehouseRes> getAll(Pageable pageable) {
        return warehouseQueryAdapter.getAll(pageable);
    }

    @Override
    public WarehouseRes getById(String id) {
        return warehouseQueryAdapter
                .getById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(WarehouseReq req) {
        warehouseCommandAdapter.save(req);
    }
}
