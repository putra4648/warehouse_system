package id.putra.wms.module.outbound.service.core;

import java.util.List;

import id.putra.wms.module.outbound.dto.PickingTaskDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface PickingService {
    ResponseData<PickingTaskDto> create(PickingTaskDto dto);
    ResponseData<PickingTaskDto> update(PickingTaskDto dto);
    ResponseData<Boolean> delete(Long id);
    ResponseData<PickingTaskDto> getById(Long id);
    ResponseData<List<PickingTaskDto>> getAll(PickingTaskDto filter);
}
