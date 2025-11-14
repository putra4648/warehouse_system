package id.putra.wms.module.inbound.service.adapter.query.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.service.adapter.query.PutawayQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PutawayQueryAdapterImpl implements PutawayQueryAdapter {

    @Override
    public Object getById(Long id) {
        // Placeholder - implement when PutawayDto and repository are ready
        return null;
    }

    @Override
    public List<Object> getAll(Object filter) {
        // Placeholder - implement when PutawayDto and repository are ready
        return List.of();
    }
}
