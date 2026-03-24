package id.putra.wms.module.inbound.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;

public interface PurchaseOrderService {
    PurchaseOrderDto create(PurchaseOrderDto dto);

    PurchaseOrderDto update(PurchaseOrderDto dto);

    Boolean delete(@NonNull Long id);

    PurchaseOrderDto getById(@NonNull Long id, @NonNull Pageable pageable);

    Page<PurchaseOrderDto> getAll(PurchaseOrderDto dto, @NonNull Pageable pageable);

    PurchaseOrderDto patch(PurchaseOrderDto dto);
}
