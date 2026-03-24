package id.putra.wms.module.inbound.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import id.putra.wms.module.inbound.dto.ReceivingDto;

public interface ReceivingQueryAdapter {
    ReceivingDto getById(@NonNull Long id, @NonNull Pageable pageable);

    Page<ReceivingDto> getAll(String search, @NonNull Pageable pageable);
}
