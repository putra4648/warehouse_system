package id.putra.wms.module.inbound.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;

public interface PurchaseOrderQueryAdapter {
    PurchaseOrderDto getById(@NonNull Long id, @NonNull Pageable pageable);

    Page<PurchaseOrderDto> getAll(PurchaseOrderDto dto, @NonNull Pageable pageable);
}
