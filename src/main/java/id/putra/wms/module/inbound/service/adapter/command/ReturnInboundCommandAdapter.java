package id.putra.wms.module.inbound.service.adapter.command;

import id.putra.wms.module.inbound.dto.ReturnDto;

public interface ReturnInboundCommandAdapter {
    ReturnDto add(ReturnDto dto);

    ReturnDto update(ReturnDto dto);

    Boolean delete(ReturnDto dto);
}
