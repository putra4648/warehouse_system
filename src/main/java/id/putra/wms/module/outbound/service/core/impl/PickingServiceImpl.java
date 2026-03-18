package id.putra.wms.module.outbound.service.core.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import id.putra.wms.module.outbound.dto.PickingTaskDto;
import id.putra.wms.module.outbound.service.adapter.command.PickingTaskCommandAdapter;
import id.putra.wms.module.outbound.service.adapter.query.PickingTaskQueryAdapter;
import id.putra.wms.module.outbound.service.core.PickingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PickingServiceImpl implements PickingService {
    private final PickingTaskCommandAdapter pickingTaskCommandAdapter;
    private final PickingTaskQueryAdapter pickingTaskQueryAdapter;

    @Override
    public PickingTaskDto create(PickingTaskDto dto) {
        pickingTaskCommandAdapter.add(List.of(dto));
        return dto;
    }

    @Override
    public PickingTaskDto update(PickingTaskDto dto) {
        pickingTaskCommandAdapter.update(List.of(dto));
        return dto;
    }

    @Override
    public Boolean delete(Long id) {
        PickingTaskDto dto = new PickingTaskDto();
        dto.setId(id);
        pickingTaskCommandAdapter.delete(List.of(dto));
        return true;
    }

    @Override
    public PickingTaskDto getById(Long id) {
        return pickingTaskQueryAdapter.getById(id);
    }

    @Override
    public List<PickingTaskDto> getAll(PickingTaskDto filter) {
        return pickingTaskQueryAdapter.getAll(filter);
    }
}
