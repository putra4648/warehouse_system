package id.putra.wms.module.warehouse.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.mapper.RackMapper;
import id.putra.wms.module.warehouse.model.repository.RackRepository;
import id.putra.wms.module.warehouse.service.adapter.command.RackCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class RackCommandAdapterImpl implements RackCommandAdapter {
    private final RackRepository rackRepository;
    private final RackMapper rackMapper;

    @Override
    public void add(List<RackDto> dtos) {
        var entities = dtos.stream().map(rackMapper::toEntity).toList();
        rackRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<RackDto> dtos) {
        var entities = dtos.stream().map(rackMapper::toEntity).toList();
        rackRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<RackDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        rackRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }

}
