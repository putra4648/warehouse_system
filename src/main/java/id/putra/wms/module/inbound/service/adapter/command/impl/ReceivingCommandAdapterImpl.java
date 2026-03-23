package id.putra.wms.module.inbound.service.adapter.command.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.mapper.ReceivingMapper;
import id.putra.wms.module.inbound.model.repository.PurchaseOrderRepository;
import id.putra.wms.module.inbound.model.repository.ReceivingRepository;
import id.putra.wms.module.inbound.service.adapter.command.ReceivingCommandAdapter;
import id.putra.wms.shared.enums.OrderStatus;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ReceivingCommandAdapterImpl implements ReceivingCommandAdapter {

    private final ReceivingRepository receivingRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ReceivingMapper receivingMapper;

    @Override
    public ReceivingDto add(ReceivingDto dto) {
        var po = purchaseOrderRepository.findById(dto.getPurchaseOrder().getId())
                .orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND));
        po.setStatus(OrderStatus.PENDING);
        purchaseOrderRepository.save(po);

        var entity = receivingMapper.toEntity(dto);
        return receivingMapper.toDto(receivingRepository.save(java.util.Objects.requireNonNull(entity)));
    }

    @Override
    public ReceivingDto update(ReceivingDto dto) {
        var entity = receivingMapper.toEntity(dto);
        return receivingMapper.toDto(receivingRepository.save(java.util.Objects.requireNonNull(entity)));
    }

    @Override
    public Boolean delete(ReceivingDto dto) {
        var id = java.util.Objects.requireNonNull(dto.getId());
        if (receivingRepository.existsById(id)) {
            receivingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ReceivingDto updateStatus(Long id, OrderStatus status) {
        var entity = receivingRepository.findById(id)
                .orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND));
        entity.setStatus(status);
        return receivingMapper.toDto(receivingRepository.save(entity));
    }
}
