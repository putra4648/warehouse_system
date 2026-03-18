package id.putra.wms.module.inbound.service.core.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import id.putra.wms.module.inbound.dto.LabellingDto;
import id.putra.wms.module.inbound.service.adapter.command.LabellingCommandAdapter;
import id.putra.wms.module.inbound.service.adapter.query.LabellingQueryAdapter;
import id.putra.wms.module.inbound.service.core.LabellingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabellingServiceImpl implements LabellingService {

    private final LabellingCommandAdapter labellingCommandAdapter;
    private final LabellingQueryAdapter labellingQueryAdapter;

    @Override
    public LabellingDto create(LabellingDto dto) {
        labellingCommandAdapter.add(List.of(dto));
        return dto;
    }

    @Override
    public LabellingDto update(LabellingDto dto) {
        labellingCommandAdapter.update(List.of(dto));
        return dto;
    }

    @Override
    public Boolean delete(Long id) {
        LabellingDto dto = new LabellingDto();
        dto.setId(id);
        labellingCommandAdapter.delete(List.of(dto));
        return true;
    }

    @Override
    public LabellingDto getById(Long id) {
        return labellingQueryAdapter.getById(id);
    }

    @Override
    public List<LabellingDto> getAll(LabellingDto filter) {
        return labellingQueryAdapter.getAll(filter);
    }
}
