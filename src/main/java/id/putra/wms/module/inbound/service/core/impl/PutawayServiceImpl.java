package id.putra.wms.module.inbound.service.core.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import id.putra.wms.module.inbound.dto.PutawayDto;
import id.putra.wms.module.inbound.service.adapter.command.PutawayCommandAdapter;
import id.putra.wms.module.inbound.service.adapter.query.PutawayQueryAdapter;
import id.putra.wms.module.inbound.service.core.PutawayService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PutawayServiceImpl implements PutawayService {

    private final PutawayCommandAdapter putawayCommandAdapter;
    private final PutawayQueryAdapter putawayQueryAdapter;

    @Override
    public PutawayDto create(PutawayDto dto) {
        putawayCommandAdapter.add(List.of(dto));
        return dto;
    }

    @Override
    public PutawayDto update(PutawayDto dto) {
        putawayCommandAdapter.update(List.of(dto));
        return dto;
    }

    @Override
    public Boolean delete(Long id) {
        PutawayDto dto = new PutawayDto();
        dto.setId(id);
        putawayCommandAdapter.delete(List.of(dto));
        return true;
    }

    @Override
    public PutawayDto getById(Long id) {
        return putawayQueryAdapter.getById(id);
    }

    @Override
    public List<PutawayDto> getAll(PutawayDto filter) {
        return putawayQueryAdapter.getAll(filter);
    }
}
