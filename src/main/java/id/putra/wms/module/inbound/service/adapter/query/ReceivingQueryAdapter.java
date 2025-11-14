package id.putra.wms.module.inbound.service.adapter.query;

import java.util.List;

import id.putra.wms.module.inbound.dto.ReceivingDto;

public interface ReceivingQueryAdapter {
    ReceivingDto getById(Long id);

    List<ReceivingDto> getAll(ReceivingDto filter);
}
