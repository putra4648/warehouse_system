package id.putra.wms.module.inbound.service.adapter.query.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.mapper.ReceivingMapper;
import id.putra.wms.module.inbound.model.repository.ReceivingRepository;
import id.putra.wms.module.inbound.service.adapter.query.ReceivingQueryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReceivingQueryAdapterImpl implements ReceivingQueryAdapter {

    private final ReceivingRepository receivingRepository;
    private final ReceivingMapper receivingMapper;

    @Override
    public ReceivingDto getById(@Nullable Long id) {
        if (id == null) return null;
        return receivingRepository.findById(id).map(receivingMapper::toDto).orElse(null);
    }

    @Override
    public List<ReceivingDto> getAll(ReceivingDto filter) {
        return receivingRepository.findAll().stream().map(receivingMapper::toDto).toList();
    }
}
