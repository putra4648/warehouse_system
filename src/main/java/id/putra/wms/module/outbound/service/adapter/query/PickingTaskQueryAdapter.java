package id.putra.wms.module.outbound.service.adapter.query;

import java.util.List;
import id.putra.wms.module.outbound.dto.PickingTaskDto;

public interface PickingTaskQueryAdapter {
    PickingTaskDto getById(Long id);
    List<PickingTaskDto> getAll(PickingTaskDto filter);
}
