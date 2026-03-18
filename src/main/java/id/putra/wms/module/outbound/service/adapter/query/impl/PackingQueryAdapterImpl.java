package id.putra.wms.module.outbound.service.adapter.query.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.putra.wms.module.outbound.dto.PackingDto;
import id.putra.wms.module.outbound.mapper.PackingMapper;
import id.putra.wms.module.outbound.model.repository.PackingRepository;
import id.putra.wms.module.outbound.service.adapter.query.PackingQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PackingQueryAdapterImpl implements PackingQueryAdapter {
    private final PackingRepository packingRepository;
    private final PackingMapper packingMapper;

    @Override
    public PackingDto getById(Long id) {
        return packingRepository.findById(id).map(packingMapper::toDto).orElse(null);
    }

    @Override
    public List<PackingDto> getAll(PackingDto filter) {
        return packingRepository.findAll().stream().map(packingMapper::toDto).toList();
    }
}
