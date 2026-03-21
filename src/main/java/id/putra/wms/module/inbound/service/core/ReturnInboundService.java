package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.ReturnDto;

public interface ReturnInboundService {
    ReturnDto create(ReturnDto dto);

    ReturnDto update(ReturnDto dto);

    Boolean delete(Long id);

    ReturnDto getById(Long id);

    List<ReturnDto> getAll(ReturnDto filter);
}
