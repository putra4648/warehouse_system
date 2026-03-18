package id.putra.wms.module.outbound.service.core.impl;

import java.util.List;
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
        salesOrderCommandAdapter.add(List.of(dto));
        return dto;
    }

    @Override
    public SalesOrderDto update(SalesOrderDto dto) {
        salesOrderCommandAdapter.update(List.of(dto));
        return dto;
    }

    @Override
    public Boolean delete(Long id) {
        SalesOrderDto dto = new SalesOrderDto();
        dto.setId(id);
        salesOrderCommandAdapter.delete(List.of(dto));
        return true;
    }

    @Override
    public SalesOrderDto getById(Long id) {
        return salesOrderQueryAdapter.getById(id);
    }

    @Override
    public List<SalesOrderDto> getAll(SalesOrderDto filter) {
        return salesOrderQueryAdapter.getAll(filter);
    }
}
