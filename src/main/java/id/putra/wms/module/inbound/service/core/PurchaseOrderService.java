package id.putra.wms.module.inbound.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;

public interface PurchaseOrderService {
    PurchaseOrderDto create(PurchaseOrderDto dto);

    PurchaseOrderDto update(PurchaseOrderDto dto);

    Boolean delete(Long id);

    PurchaseOrderDto getById(Long id);

    Page<PurchaseOrderDto> getAll(String search, Pageable pageable);
}
