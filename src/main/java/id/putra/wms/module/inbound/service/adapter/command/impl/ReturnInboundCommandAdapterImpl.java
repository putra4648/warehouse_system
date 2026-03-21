package id.putra.wms.module.inbound.service.adapter.command.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.ReturnDto;
import id.putra.wms.module.inbound.service.adapter.command.ReturnInboundCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ReturnInboundCommandAdapterImpl implements ReturnInboundCommandAdapter {

    @Override
    public ReturnDto add(ReturnDto dto) {
        // TODO: Placeholder - implement when repository is ready
        return dto;
    }

    @Override
    public ReturnDto update(ReturnDto dto) {
        // TODO: Placeholder - implement when repository is ready
        return dto;
    }

    @Override
    public Boolean delete(ReturnDto dto) {
        // TODO: Placeholder - implement when repository is ready
        return false;
    }
}
