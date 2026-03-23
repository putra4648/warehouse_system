package id.putra.wms.module.inbound.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.shared.enums.OrderStatus;

public interface ReceivingOrderService {
    ReceivingDto create(ReceivingDto dto);

    ReceivingDto update(ReceivingDto dto);

    Boolean delete(Long id);

    ReceivingDto getById(Long id, Pageable pageable);

    Page<ReceivingDto> getAll(String search, Pageable pageable);

    ReceivingDto updateStatus(Long id, OrderStatus status);
}
