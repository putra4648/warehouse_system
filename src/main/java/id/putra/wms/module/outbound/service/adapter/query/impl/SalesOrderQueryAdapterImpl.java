package id.putra.wms.module.outbound.service.adapter.query.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.mapper.SalesOrderMapper;
import id.putra.wms.module.outbound.model.repository.SalesOrderRepository;
import id.putra.wms.module.outbound.service.adapter.query.SalesOrderQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SalesOrderQueryAdapterImpl implements SalesOrderQueryAdapter {
    private final SalesOrderRepository salesOrderRepository;
    private final SalesOrderMapper salesOrderMapper;

    @Override
    public SalesOrderDto getById(Long id) {
        return salesOrderRepository.findById(id).map(salesOrderMapper::toDto).orElse(null);
    }

    @Override
    public List<SalesOrderDto> getAll(SalesOrderDto filter) {
        return salesOrderRepository.findAll().stream().map(salesOrderMapper::toDto).toList();
    }
}
