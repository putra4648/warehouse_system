package id.putra.wms.module.inbound.service.adapter.command.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.PutawayDto;
import id.putra.wms.module.inbound.service.adapter.command.PutawayCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class PutawayCommandAdapterImpl implements PutawayCommandAdapter {

    @Override
    public PutawayDto add(PutawayDto dto) {
        // TODO: Placeholder - implement when repository is ready
        return dto;
    }

    @Override
    public PutawayDto update(PutawayDto dto) {
        // TODO: Placeholder - implement when repository is ready
        return dto;
    }

    @Override
    public Boolean delete(PutawayDto dto) {
        // TODO: Placeholder - implement when repository is ready
        return false;
    }
}
