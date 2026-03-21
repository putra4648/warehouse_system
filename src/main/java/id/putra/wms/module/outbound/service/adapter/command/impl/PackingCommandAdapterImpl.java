package id.putra.wms.module.outbound.service.adapter.command.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.putra.wms.module.outbound.dto.PackingDto;
import id.putra.wms.module.outbound.mapper.PackingMapper;
import id.putra.wms.module.outbound.model.repository.PackingRepository;
import id.putra.wms.module.outbound.service.adapter.command.PackingCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PackingCommandAdapterImpl implements PackingCommandAdapter {
    private final PackingRepository packingRepository;
    private final PackingMapper packingMapper;

    @Override
    public void add(List<PackingDto> dtos) {
        var entities = dtos.stream().map(packingMapper::toEntity).toList();
        packingRepository.saveAll(entities);
    }

    @Override
    public void update(List<PackingDto> dtos) {
        var entities = dtos.stream().map(packingMapper::toEntity).toList();
        packingRepository.saveAll(entities);
    }

    @Override
    public void delete(List<PackingDto> dtos) {
        var ids = dtos.stream().map(PackingDto::getId).toList();
        packingRepository.deleteAllById(ids);
    }
}
