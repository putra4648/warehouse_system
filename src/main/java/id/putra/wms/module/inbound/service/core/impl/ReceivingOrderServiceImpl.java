package id.putra.wms.module.inbound.service.core.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.service.adapter.command.ReceivingCommandAdapter;
import id.putra.wms.module.inbound.service.adapter.query.ReceivingQueryAdapter;
import id.putra.wms.module.inbound.service.core.ReceivingOrderService;
import id.putra.wms.shared.enums.OrderStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceivingOrderServiceImpl implements ReceivingOrderService {

    private final ReceivingCommandAdapter receivingCommandAdapter;
    private final ReceivingQueryAdapter receivingQueryAdapter;

    @Override
    public ReceivingDto create(ReceivingDto dto) {
        return receivingCommandAdapter.add(dto);
    }

    @Override
    public ReceivingDto update(ReceivingDto dto) {
        return receivingCommandAdapter.update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        ReceivingDto dto = new ReceivingDto();
        dto.setId(id);
        return receivingCommandAdapter.delete(dto);
    }

    @Override
    public ReceivingDto getById(Long id, Pageable pageable) {
        return receivingQueryAdapter.getById(id, pageable);
    }

    @Override
    public Page<ReceivingDto> getAll(String search, Pageable pageable) {
        return receivingQueryAdapter.getAll(search, pageable);
    }

    @Override
    public ReceivingDto updateStatus(Long id, OrderStatus status) {
        return receivingCommandAdapter.updateStatus(id, status);
    }
}
