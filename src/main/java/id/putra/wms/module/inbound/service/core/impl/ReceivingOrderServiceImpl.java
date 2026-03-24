package id.putra.wms.module.inbound.service.core.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
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
    public Boolean delete(@NonNull Long id) {
        return receivingCommandAdapter.delete(id);
    }

    @Override
    public ReceivingDto getById(@NonNull Long id, @NonNull Pageable pageable) {
        return receivingQueryAdapter.getById(id, pageable);
    }

    @Override
    public Page<ReceivingDto> getAll(String search, @NonNull Pageable pageable) {
        return receivingQueryAdapter.getAll(search, pageable);
    }

    @Override
    public ReceivingDto patch(ReceivingDto body) {
        return receivingCommandAdapter.patch(body);
    }
}
