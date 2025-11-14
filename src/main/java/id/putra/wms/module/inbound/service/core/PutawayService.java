package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.PutawayDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface PutawayService {
    ResponseData<Object> create(PutawayDto dto);
    ResponseData<Object> update(PutawayDto dto);
    ResponseData<Boolean> delete(Long id);
    ResponseData<Object> getById(Long id);
    ResponseData<List<Object>> getAll(PutawayDto filter);
}
