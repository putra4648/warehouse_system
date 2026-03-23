package id.putra.wms.module.inbound.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.shared.enums.OrderStatus;

public interface PurchaseOrderService {
    PurchaseOrderDto create(PurchaseOrderDto dto);

    PurchaseOrderDto update(PurchaseOrderDto dto);

    Boolean delete(Long id);

    PurchaseOrderDto getById(Long id, Pageable pageable);

    Page<PurchaseOrderDto> getAll(PurchaseOrderDto dto, Pageable pageable);

    PurchaseOrderDto updateStatus(Long id, OrderStatus status);
}
