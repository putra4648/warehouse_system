package id.putra.wms.module.outbound.service.adapter.query.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.putra.wms.module.outbound.dto.PickingTaskDto;
import id.putra.wms.module.outbound.mapper.PickingTaskMapper;
import id.putra.wms.module.outbound.model.repository.PickingTaskRepository;
import id.putra.wms.module.outbound.service.adapter.query.PickingTaskQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PickingTaskQueryAdapterImpl implements PickingTaskQueryAdapter {
    private final PickingTaskRepository pickingTaskRepository;
    private final PickingTaskMapper pickingTaskMapper;

    @Override
    public PickingTaskDto getById(Long id) {
        return pickingTaskRepository.findById(id).map(pickingTaskMapper::toDto).orElse(null);
    }

    @Override
    public List<PickingTaskDto> getAll(PickingTaskDto filter) {
        return pickingTaskRepository.findAll().stream().map(pickingTaskMapper::toDto).toList();
    }
}
