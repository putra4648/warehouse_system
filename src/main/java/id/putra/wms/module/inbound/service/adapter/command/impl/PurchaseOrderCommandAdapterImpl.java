package id.putra.wms.module.inbound.service.adapter.command.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.PurchaseOrderDto;
import id.putra.wms.module.inbound.mapper.PurchaseOrderMapper;
import id.putra.wms.module.inbound.model.repository.PurchaseOrderRepository;
import id.putra.wms.module.inbound.service.adapter.command.PurchaseOrderCommandAdapter;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
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
        return purchaseOrderMapper.toDto(purchaseOrderRepository.save(Objects.requireNonNull(entity)));
    }

    @Override
    public PurchaseOrderDto update(PurchaseOrderDto dto) {
        var entity = purchaseOrderMapper.toEntity(dto);
        return purchaseOrderMapper.toDto(purchaseOrderRepository.save(Objects.requireNonNull(entity)));
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            throw new ModuleException(ResponseEnum.INVALID_PARAM);
        }
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PurchaseOrderDto patch(PurchaseOrderDto dto) {
        var entity = purchaseOrderRepository.findById(Objects.requireNonNull(dto.getId()))
                .orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND));
        purchaseOrderMapper.patchDtoToEntity(dto, entity);
        return purchaseOrderMapper.toDto(purchaseOrderRepository.save(Objects.requireNonNull(entity)));
    }
}
