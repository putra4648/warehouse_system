package id.putra.wms.module.inbound.service.adapter.query;

import java.util.List;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;

public interface PurchaseOrderQueryAdapter {
    PurchaseOrderDto getById(Long id);

    List<PurchaseOrderDto> getAll(PurchaseOrderDto filter);
}
