package id.putra.wms.module.inbound.service.adapter.query.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.service.adapter.query.LabellingQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LabellingQueryAdapterImpl implements LabellingQueryAdapter {

    @Override
    public Object getById(Long id) {
        // Placeholder - implement when LabelDto and repository are ready
        return null;
    }

    @Override
    public List<Object> getAll(Object filter) {
        // Placeholder - implement when LabelDto and repository are ready
        return List.of();
    }
}
