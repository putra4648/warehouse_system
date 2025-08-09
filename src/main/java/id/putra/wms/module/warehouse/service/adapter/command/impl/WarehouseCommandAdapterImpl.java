package id.putra.wms.module.warehouse.service.adapter.command.impl;

import id.putra.wms.module.warehouse.dto.request.WarehouseReq;
import id.putra.wms.module.warehouse.mapper.WarehouseMapper;
import id.putra.wms.module.warehouse.model.repository.WarehouseRepository;
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseCommandAdapterImpl implements WarehouseCommandAdapter {

    private final WarehouseRepository warehouseRepository;

    private final WarehouseMapper warehouseMapper;

    @Override
    public void save(WarehouseReq req) {
        warehouseRepository.save(warehouseMapper.toEntity(req));
    }

}
