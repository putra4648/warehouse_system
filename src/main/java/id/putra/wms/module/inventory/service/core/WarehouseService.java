package id.putra.wms.module.inventory.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseService implements WarehouseCoreService {

    private final WarehouseQueryAdapter warehouseQueryAdapter;
    private final WarehouseCommandAdapter warehouseCommandAdapter;

    @Override
    public Page<WarehouseDto> getWarehouses(WarehouseDto dto, Pageable pageable) {
        return warehouseQueryAdapter.getWarehouses(dto, pageable);
    }

    @Override
    public void save(WarehouseDto req) {
        warehouseCommandAdapter.save(req);
    }

    @Override
    public WarehouseDto getWarehouse(WarehouseDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWarehouseById'");
    }

}
