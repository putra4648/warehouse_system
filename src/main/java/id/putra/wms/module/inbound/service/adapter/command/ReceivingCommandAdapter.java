package id.putra.wms.module.inbound.service.adapter.command;

import id.putra.wms.module.inbound.dto.ReceivingDto;

public interface ReceivingCommandAdapter {
    ReceivingDto add(ReceivingDto dto);

    ReceivingDto update(ReceivingDto dto);

    Boolean delete(ReceivingDto dto);
}
