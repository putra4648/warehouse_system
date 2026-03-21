package id.putra.wms.module.inbound.service.adapter.command;

import id.putra.wms.module.inbound.dto.PutawayDto;

public interface PutawayCommandAdapter {
    PutawayDto add(PutawayDto dto);

    PutawayDto update(PutawayDto dto);

    Boolean delete(PutawayDto dto);
}
