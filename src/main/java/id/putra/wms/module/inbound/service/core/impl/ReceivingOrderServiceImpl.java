package id.putra.wms.module.inbound.service.core.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.service.adapter.command.ReceivingCommandAdapter;
import id.putra.wms.module.inbound.service.adapter.query.ReceivingQueryAdapter;
import id.putra.wms.module.inbound.service.core.ReceivingOrderService;
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
    public ReceivingDto getById(Long id) {
        return receivingQueryAdapter.getById(id);
    }

    @Override
    public List<ReceivingDto> getAll(ReceivingDto filter) {
        return receivingQueryAdapter.getAll(filter);
    }
}
