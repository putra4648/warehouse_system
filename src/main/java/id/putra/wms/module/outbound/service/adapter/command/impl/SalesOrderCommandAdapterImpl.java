package id.putra.wms.module.outbound.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.mapper.SalesOrderMapper;
import id.putra.wms.module.outbound.model.repository.SalesOrderRepository;
import id.putra.wms.module.outbound.service.adapter.command.SalesOrderCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class SalesOrderCommandAdapterImpl implements SalesOrderCommandAdapter {

    private final SalesOrderRepository salesOrderRepository;
    private final SalesOrderMapper salesOrderMapper;

    @Override
    public void add(List<SalesOrderDto> dtos) {
        var entities = dtos.stream().map(salesOrderMapper::toEntity).toList();
        salesOrderRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<SalesOrderDto> dtos) {
        var entities = dtos.stream().map(salesOrderMapper::toEntity).toList();
        salesOrderRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<SalesOrderDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        salesOrderRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }
}
