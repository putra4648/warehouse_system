package id.putra.wms.module.outbound.service.adapter.command.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.outbound.converter.SalesOrderConverter;
import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.module.outbound.model.repository.SalesOrderRepository;
import id.putra.wms.module.outbound.service.adapter.command.SalesOrderCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class SalesOrderCommandAdapterImpl implements SalesOrderCommandAdapter {

    private final SalesOrderRepository salesOrderRepository;

    @Override
    public SalesOrderDto add(SalesOrderDto dto) {
        var entity = SalesOrderConverter.toEntity(dto);
        return SalesOrderConverter.toDto(salesOrderRepository.save(java.util.Objects.requireNonNull(entity)));
    }

    @Override
    public SalesOrderDto update(SalesOrderDto dto) {
        var entity = SalesOrderConverter.toEntity(dto);
        return SalesOrderConverter.toDto(salesOrderRepository.save(java.util.Objects.requireNonNull(entity)));
    }

    @Override
    public Boolean delete(Long id) {
        if (salesOrderRepository.existsById(id)) {
            salesOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
