package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.PutawayDto;

public interface PutawayService {
    PutawayDto create(PutawayDto dto);

    PutawayDto update(PutawayDto dto);

    Boolean delete(Long id);

    PutawayDto getById(Long id);

    List<PutawayDto> getAll(PutawayDto filter);
}
