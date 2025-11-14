package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.ReturnDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface ReturnInboundService {
    ResponseData<Object> create(ReturnDto dto);
    ResponseData<Object> update(ReturnDto dto);
    ResponseData<Boolean> delete(Long id);
    ResponseData<Object> getById(Long id);
    ResponseData<List<Object>> getAll(ReturnDto filter);
}
