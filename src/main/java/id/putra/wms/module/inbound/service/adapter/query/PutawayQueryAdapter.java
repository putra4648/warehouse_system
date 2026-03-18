package id.putra.wms.module.inbound.service.adapter.query;

import java.util.List;

import id.putra.wms.module.inbound.dto.PutawayDto;

public interface PutawayQueryAdapter {
    PutawayDto getById(Long id);

    List<PutawayDto> getAll(PutawayDto filter);
}
