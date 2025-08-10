package id.putra.wms.module.warehouse.service.adapter.command.impl;

import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.form.WarehouseForm;
import id.putra.wms.module.warehouse.mapper.WarehouseMapper;
import id.putra.wms.module.warehouse.model.repository.WarehouseRepository;
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseCommandAdapterImpl implements WarehouseCommandAdapter {

    private final WarehouseRepository warehouseRepository;

    private final WarehouseMapper warehouseMapper;

    @Override
    public void save(WarehouseForm req) {
        warehouseRepository.save(warehouseMapper.toEntity(req));
    }

}
