package id.putra.wms.module.inbound.service.adapter.query.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.mapper.PurchaseOrderMapper;
import id.putra.wms.module.inbound.model.repository.PurchaseOrderRepository;
import id.putra.wms.module.inbound.service.adapter.query.PurchaseOrderQueryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchaseOrderQueryAdapterImpl implements PurchaseOrderQueryAdapter {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public PurchaseOrderDto getById(@Nullable Long id) {
        if (id == null) return null;
        return purchaseOrderRepository.findById(id).map(purchaseOrderMapper::toDto).orElse(null);
    }

    @Override
    public List<PurchaseOrderDto> getAll(PurchaseOrderDto filter) {
        return purchaseOrderRepository.findAll().stream().map(purchaseOrderMapper::toDto).toList();
    }
}
