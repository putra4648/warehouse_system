package id.putra.wms.module.outbound.service.core.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import id.putra.wms.module.outbound.dto.PackingDto;
import id.putra.wms.module.outbound.service.adapter.command.PackingCommandAdapter;
import id.putra.wms.module.outbound.service.adapter.query.PackingQueryAdapter;
import id.putra.wms.module.outbound.service.core.PackingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackingServiceImpl implements PackingService {
    private final PackingCommandAdapter packingCommandAdapter;
    private final PackingQueryAdapter packingQueryAdapter;

    @Override
    public PackingDto create(PackingDto dto) {
        packingCommandAdapter.add(List.of(dto));
        return dto;
    }

    @Override
    public PackingDto update(PackingDto dto) {
        packingCommandAdapter.update(List.of(dto));
        return dto;
    }

    @Override
    public Boolean delete(Long id) {
        PackingDto dto = new PackingDto();
        dto.setId(id);
        packingCommandAdapter.delete(List.of(dto));
        return true;
    }

    @Override
    public PackingDto getById(Long id) {
        return packingQueryAdapter.getById(id);
    }

    @Override
    public List<PackingDto> getAll(PackingDto filter) {
        return packingQueryAdapter.getAll(filter);
    }
}
