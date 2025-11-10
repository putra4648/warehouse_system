package id.putra.wms.module.warehouse.service.core.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.service.adapter.command.RackCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.RackQueryAdapter;
import id.putra.wms.module.warehouse.service.core.RackCoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RackServiceImpl implements RackCoreService {
    private final RackQueryAdapter rackQueryAdapter;
    private final RackCommandAdapter rackCommandAdapter;

    @Override
    public Page<RackDto> getRacks(RackDto dto, Pageable pageable) {
        return rackQueryAdapter.getRacks(dto, pageable);
    }

    @Override
    public RackDto getRack(RackDto rackDto) {
        return rackQueryAdapter.getRackById(rackDto);
    }

    @Override
    public void add(List<RackDto> dtos) {
        rackCommandAdapter.add(dtos);
    }

    @Override
    public void update(List<RackDto> dtos) {
        rackCommandAdapter.update(dtos);
    }

    @Override
    public void delete(List<RackDto> dtos) {
        rackCommandAdapter.delete(dtos);
    }

}
