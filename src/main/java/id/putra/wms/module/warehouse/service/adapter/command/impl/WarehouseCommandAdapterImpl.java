package id.putra.wms.module.warehouse.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.mapper.WarehouseMapper;
import id.putra.wms.module.warehouse.model.repository.WarehouseRepository;
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class WarehouseCommandAdapterImpl implements WarehouseCommandAdapter {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public void save(List<WarehouseDto> dtos) {
        var entities = dtos.stream().map(d -> warehouseMapper.toEntity(d)).toList();
        warehouseRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<WarehouseDto> dtos) {
        var entities = dtos.stream().map(d -> warehouseMapper.toEntity(d)).toList();
        warehouseRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<WarehouseDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        warehouseRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }

}
