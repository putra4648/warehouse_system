package id.putra.wms.module.inbound.service.adapter.command;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;

public interface PurchaseOrderCommandAdapter {
    PurchaseOrderDto add(PurchaseOrderDto dto);

    PurchaseOrderDto update(PurchaseOrderDto dto);

    Boolean delete(Long id);

    PurchaseOrderDto patch(PurchaseOrderDto dto);
}
