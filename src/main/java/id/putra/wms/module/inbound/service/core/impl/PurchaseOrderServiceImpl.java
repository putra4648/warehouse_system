package id.putra.wms.module.inbound.service.core.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return purchaseOrderCommandAdapter.add(dto);
    }

    @Override
    public PurchaseOrderDto update(PurchaseOrderDto dto) {
        return purchaseOrderCommandAdapter.update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        PurchaseOrderDto dto = new PurchaseOrderDto();
        dto.setId(id);
        return purchaseOrderCommandAdapter.delete(dto);
    }

    @Override
    public PurchaseOrderDto getById(Long id) {
        return purchaseOrderQueryAdapter.getById(id);
    }

    @Override
    public Page<PurchaseOrderDto> getAll(String search, Pageable pageable) {
        return purchaseOrderQueryAdapter.getAll(search, pageable);
    }
}
