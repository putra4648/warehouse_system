package id.putra.wms.module.outbound.service.adapter.command;

import java.util.List;

import id.putra.wms.module.outbound.dto.PickingTaskDto;

public interface PickingTaskCommandAdapter {
    void add(List<PickingTaskDto> dtos);

    void update(List<PickingTaskDto> dtos);

    void delete(List<PickingTaskDto> dtos);
}
