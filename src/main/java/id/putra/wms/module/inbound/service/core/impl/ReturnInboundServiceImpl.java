package id.putra.wms.module.inbound.service.core.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import id.putra.wms.module.inbound.dto.ReturnDto;
import id.putra.wms.module.inbound.service.adapter.command.ReturnInboundCommandAdapter;
import id.putra.wms.module.inbound.service.adapter.query.ReturnInboundQueryAdapter;
import id.putra.wms.module.inbound.service.core.ReturnInboundService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReturnInboundServiceImpl implements ReturnInboundService {

    private final ReturnInboundCommandAdapter returnInboundCommandAdapter;
    private final ReturnInboundQueryAdapter returnInboundQueryAdapter;

    @Override
    public ReturnDto create(ReturnDto dto) {
        return returnInboundCommandAdapter.add(dto);
    }

    @Override
    public ReturnDto update(ReturnDto dto) {
        return returnInboundCommandAdapter.update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        ReturnDto dto = new ReturnDto();
        dto.setId(id);
        return returnInboundCommandAdapter.delete(dto);
    }

    @Override
    public ReturnDto getById(Long id) {
        return returnInboundQueryAdapter.getById(id);
    }

    @Override
    public List<ReturnDto> getAll(ReturnDto filter) {
        return returnInboundQueryAdapter.getAll(filter);
    }
}
