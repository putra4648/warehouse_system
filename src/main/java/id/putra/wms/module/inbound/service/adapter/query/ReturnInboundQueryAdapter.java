package id.putra.wms.module.inbound.service.adapter.query;

import java.util.List;

import id.putra.wms.module.inbound.dto.ReturnDto;

public interface ReturnInboundQueryAdapter {
    ReturnDto getById(Long id);

    List<ReturnDto> getAll(ReturnDto filter);
}
