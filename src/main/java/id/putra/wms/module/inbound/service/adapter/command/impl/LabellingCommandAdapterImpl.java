package id.putra.wms.module.inbound.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.service.adapter.command.LabellingCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class LabellingCommandAdapterImpl implements LabellingCommandAdapter {

    @Override
    public void add(List<Object> dtos) {
        // Placeholder - implement when LabelDto and repository are ready
    }

    @Override
    public void update(List<Object> dtos) {
        // Placeholder - implement when LabelDto and repository are ready
    }

    @Override
    public void delete(List<Object> dtos) {
        // Placeholder - implement when LabelDto and repository are ready
    }
}
