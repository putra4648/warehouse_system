package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.LabellingDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface LabellingService {
    ResponseData<Object> create(LabellingDto dto);
    ResponseData<Object> update(LabellingDto dto);
    ResponseData<Boolean> delete(Long id);
    ResponseData<Object> getById(Long id);
    ResponseData<List<Object>> getAll(LabellingDto filter);
}
