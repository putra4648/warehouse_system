package id.putra.wms.module.inbound.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.inbound.dto.ReceivingDto;

public interface ReceivingQueryAdapter {
    ReceivingDto getById(Long id, Pageable pageable);

    Page<ReceivingDto> getAll(String search, Pageable pageable);
}
