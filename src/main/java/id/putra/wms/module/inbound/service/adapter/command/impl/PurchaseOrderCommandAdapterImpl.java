package id.putra.wms.module.inbound.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.mapper.PurchaseOrderMapper;
import id.putra.wms.module.inbound.model.repository.PurchaseOrderRepository;
import id.putra.wms.module.inbound.service.adapter.command.PurchaseOrderCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class PurchaseOrderCommandAdapterImpl implements PurchaseOrderCommandAdapter {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public void add(List<PurchaseOrderDto> dtos) {
        var entities = dtos.stream().map(purchaseOrderMapper::toEntity).toList();
        purchaseOrderRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<PurchaseOrderDto> dtos) {
        var entities = dtos.stream().map(purchaseOrderMapper::toEntity).toList();
        purchaseOrderRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<PurchaseOrderDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        purchaseOrderRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }
}
