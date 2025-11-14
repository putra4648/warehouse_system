package id.putra.wms.module.inbound.service.adapter.command;

import java.util.List;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;

public interface PurchaseOrderCommandAdapter {
    void add(List<PurchaseOrderDto> dtos);

    void update(List<PurchaseOrderDto> dtos);

    void delete(List<PurchaseOrderDto> dtos);
}
