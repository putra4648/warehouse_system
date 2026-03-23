package id.putra.wms.module.inbound.service.adapter.command;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.shared.enums.OrderStatus;

public interface PurchaseOrderCommandAdapter {
    PurchaseOrderDto add(PurchaseOrderDto dto);

    PurchaseOrderDto update(PurchaseOrderDto dto);

    Boolean delete(Long id);

    PurchaseOrderDto updateStatus(Long id, OrderStatus status);
}
