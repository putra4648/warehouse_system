package id.putra.wms.module.outbound.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.outbound.dto.PickingTaskDto;
import id.putra.wms.module.outbound.mapper.PickingTaskMapper;
import id.putra.wms.module.outbound.model.repository.PickingTaskRepository;
import id.putra.wms.module.outbound.service.adapter.command.PickingTaskCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class PickingTaskCommandAdapterImpl implements PickingTaskCommandAdapter {

    private final PickingTaskRepository pickingTaskRepository;
    private final PickingTaskMapper pickingTaskMapper;

    @Override
    public void add(List<PickingTaskDto> dtos) {
        var entities = dtos.stream().map(pickingTaskMapper::toEntity).toList();
        pickingTaskRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<PickingTaskDto> dtos) {
        var entities = dtos.stream().map(pickingTaskMapper::toEntity).toList();
        pickingTaskRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<PickingTaskDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        pickingTaskRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }
}
