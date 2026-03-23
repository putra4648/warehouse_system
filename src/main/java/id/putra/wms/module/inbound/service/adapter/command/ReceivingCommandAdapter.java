package id.putra.wms.module.inbound.service.adapter.command;

import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.shared.enums.OrderStatus;

public interface ReceivingCommandAdapter {
    ReceivingDto add(ReceivingDto dto);

    ReceivingDto update(ReceivingDto dto);

    Boolean delete(ReceivingDto dto);

    ReceivingDto updateStatus(Long id, OrderStatus status);
}
