package id.putra.wms.module.inbound.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.service.adapter.command.ReturnInboundCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ReturnInboundCommandAdapterImpl implements ReturnInboundCommandAdapter {

    @Override
    public void add(List<Object> dtos) {
        // Placeholder - implement when ReturnInboundDto and repository are ready
    }

    @Override
    public void update(List<Object> dtos) {
        // Placeholder - implement when ReturnInboundDto and repository are ready
    }

    @Override
    public void delete(List<Object> dtos) {
        // Placeholder - implement when ReturnInboundDto and repository are ready
    }
}
