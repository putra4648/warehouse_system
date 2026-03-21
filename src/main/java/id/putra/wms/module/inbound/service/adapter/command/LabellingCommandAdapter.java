package id.putra.wms.module.inbound.service.adapter.command;

import id.putra.wms.module.inbound.dto.LabellingDto;

public interface LabellingCommandAdapter {
    LabellingDto add(LabellingDto dto);

    LabellingDto update(LabellingDto dto);

    Boolean delete(LabellingDto dto);
}
