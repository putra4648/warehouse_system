package id.putra.wms.module.inbound.service.core.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.service.adapter.command.PurchaseOrderCommandAdapter;
import id.putra.wms.module.inbound.service.adapter.query.PurchaseOrderQueryAdapter;
import id.putra.wms.module.inbound.service.core.PurchaseOrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderCommandAdapter purchaseOrderCommandAdapter;
    private final PurchaseOrderQueryAdapter purchaseOrderQueryAdapter;

    @Override
    public PurchaseOrderDto create(PurchaseOrderDto dto) {
        purchaseOrderCommandAdapter.add(List.of(dto));
        return dto;
    }

    @Override
    public PurchaseOrderDto update(PurchaseOrderDto dto) {
        purchaseOrderCommandAdapter.update(List.of(dto));
        return dto;
    }

    @Override
    public Boolean delete(Long id) {
        PurchaseOrderDto dto = new PurchaseOrderDto();
        dto.setId(id);
        purchaseOrderCommandAdapter.delete(List.of(dto));
        return true;
    }

    @Override
    public PurchaseOrderDto getById(Long id) {
        return purchaseOrderQueryAdapter.getById(id);
    }

    @Override
    public List<PurchaseOrderDto> getAll(PurchaseOrderDto filter) {
        return purchaseOrderQueryAdapter.getAll(filter);
    }
}
