package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface PurchaseOrderService {
    ResponseData<PurchaseOrderDto> create(PurchaseOrderDto dto);
    ResponseData<PurchaseOrderDto> update(PurchaseOrderDto dto);
    ResponseData<Boolean> delete(Long id);
    ResponseData<PurchaseOrderDto> getById(Long id);
    ResponseData<List<PurchaseOrderDto>> getAll(PurchaseOrderDto filter);
}
