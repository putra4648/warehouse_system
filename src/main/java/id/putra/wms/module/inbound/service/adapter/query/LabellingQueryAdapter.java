package id.putra.wms.module.inbound.service.adapter.query;

import java.util.List;

import id.putra.wms.module.inbound.dto.LabellingDto;

public interface LabellingQueryAdapter {
    LabellingDto getById(Long id);

    List<LabellingDto> getAll(LabellingDto filter);
}
