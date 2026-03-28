package id.putra.wms.module.inbound.service.core.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
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
    public Boolean delete(@NonNull Long id) {
        return purchaseOrderCommandAdapter.delete(id);
    }

    @Override
    public PurchaseOrderDto getById(@NonNull Long id, @NonNull Pageable pageable) {
        return purchaseOrderQueryAdapter.getById(id, pageable);
    }

    @Override
    public Page<PurchaseOrderDto> getAll(PurchaseOrderDto dto, @NonNull Pageable pageable) {
        return purchaseOrderQueryAdapter.getAll(dto, pageable);
    }

    @Override
    public PurchaseOrderDto patch(PurchaseOrderDto dto) {
        return purchaseOrderCommandAdapter.patch(dto);
    }
}
