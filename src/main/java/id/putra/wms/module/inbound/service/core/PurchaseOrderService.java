package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;

public interface PurchaseOrderService {
    PurchaseOrderDto create(PurchaseOrderDto dto);

    PurchaseOrderDto update(PurchaseOrderDto dto);

    Boolean delete(Long id);

    PurchaseOrderDto getById(Long id);

    List<PurchaseOrderDto> getAll(PurchaseOrderDto filter);
}
