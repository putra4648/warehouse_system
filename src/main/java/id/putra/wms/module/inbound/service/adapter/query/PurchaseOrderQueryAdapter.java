package id.putra.wms.module.inbound.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;

public interface PurchaseOrderQueryAdapter {
    PurchaseOrderDto getById(Long id);

    Page<PurchaseOrderDto> getAll(String search, Pageable pageable);
}
