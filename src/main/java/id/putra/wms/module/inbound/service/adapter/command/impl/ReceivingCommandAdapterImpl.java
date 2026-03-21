package id.putra.wms.module.inbound.service.adapter.command.impl;

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
    public ReceivingDto add(ReceivingDto dto) {
        var entity = receivingMapper.toEntity(dto);
        return receivingMapper.toDto(receivingRepository.save(java.util.Objects.requireNonNull(entity)));
    }

    @Override
    public ReceivingDto update(ReceivingDto dto) {
        var entity = receivingMapper.toEntity(dto);
        return receivingMapper.toDto(receivingRepository.save(java.util.Objects.requireNonNull(entity)));
    }

    @Override
    public Boolean delete(ReceivingDto dto) {
        var id = java.util.Objects.requireNonNull(dto.getId());
        if (receivingRepository.existsById(id)) {
            receivingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
