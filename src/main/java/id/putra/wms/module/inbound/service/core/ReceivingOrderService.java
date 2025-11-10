package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface ReceivingOrderService {
    ResponseData<ReceivingDto> create(ReceivingDto dto);
    ResponseData<ReceivingDto> update(ReceivingDto dto);
    ResponseData<Boolean> delete(Long id);
    ResponseData<ReceivingDto> getById(Long id);
    ResponseData<List<ReceivingDto>> getAll(ReceivingDto filter);
}
