package id.putra.wms.module.inbound.service.adapter.command;

import java.util.List;

import id.putra.wms.module.inbound.dto.ReceivingDto;

public interface ReceivingCommandAdapter {
    void add(List<ReceivingDto> dtos);

    void update(List<ReceivingDto> dtos);

    void delete(List<ReceivingDto> dtos);
}
