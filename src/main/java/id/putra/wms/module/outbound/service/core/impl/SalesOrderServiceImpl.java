package id.putra.wms.module.outbound.service.core.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.service.adapter.command.SalesOrderCommandAdapter;
import id.putra.wms.module.outbound.service.adapter.query.SalesOrderQueryAdapter;
import id.putra.wms.module.outbound.service.core.SalesOrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesOrderServiceImpl implements SalesOrderService {
    private final SalesOrderCommandAdapter salesOrderCommandAdapter;
    private final SalesOrderQueryAdapter salesOrderQueryAdapter;

    @Override
    public SalesOrderDto create(SalesOrderDto dto) {
        return salesOrderCommandAdapter.add(dto);
    }

    @Override
    public SalesOrderDto update(SalesOrderDto dto) {
        return salesOrderCommandAdapter.update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        return salesOrderCommandAdapter.delete(id);
    }

    @Override
    public SalesOrderDto getById(Long id) {
        return salesOrderQueryAdapter.getById(id);
    }

    @Override
    public Page<SalesOrderDto> getAll(String search, Pageable pageable) {
        return salesOrderQueryAdapter.getAll(search, pageable);
    }
}
