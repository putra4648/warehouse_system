package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.ReceivingDto;

public interface ReceivingOrderService {
    ReceivingDto create(ReceivingDto dto);

    ReceivingDto update(ReceivingDto dto);

    Boolean delete(Long id);

    ReceivingDto getById(Long id);

    List<ReceivingDto> getAll(ReceivingDto filter);
}
