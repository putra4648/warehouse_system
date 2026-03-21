package id.putra.wms.module.inbound.service.adapter.command.impl;

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
    public PurchaseOrderDto add(PurchaseOrderDto dto) {
        var entity = purchaseOrderMapper.toEntity(dto);
        return purchaseOrderMapper.toDto(purchaseOrderRepository.save(java.util.Objects.requireNonNull(entity)));
    }

    @Override
    public PurchaseOrderDto update(PurchaseOrderDto dto) {
        var entity = purchaseOrderMapper.toEntity(dto);
        return purchaseOrderMapper.toDto(purchaseOrderRepository.save(java.util.Objects.requireNonNull(entity)));
    }

    @Override
    public Boolean delete(PurchaseOrderDto dto) {
        var id = java.util.Objects.requireNonNull(dto.getId());
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
