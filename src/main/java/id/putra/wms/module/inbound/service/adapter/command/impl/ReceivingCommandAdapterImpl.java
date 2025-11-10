package id.putra.wms.module.inbound.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.mapper.ReceivingMapper;
import id.putra.wms.module.inbound.model.repository.ReceivingRepository;
import id.putra.wms.module.inbound.service.adapter.command.ReceivingCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ReceivingCommandAdapterImpl implements ReceivingCommandAdapter {

    private final ReceivingRepository receivingRepository;
    private final ReceivingMapper receivingMapper;

    @Override
    public void add(List<ReceivingDto> dtos) {
        var entities = dtos.stream().map(receivingMapper::toEntity).toList();
        receivingRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<ReceivingDto> dtos) {
        var entities = dtos.stream().map(receivingMapper::toEntity).toList();
        receivingRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<ReceivingDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        receivingRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }
}
