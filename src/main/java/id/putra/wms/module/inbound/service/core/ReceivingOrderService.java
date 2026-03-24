package id.putra.wms.module.inbound.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import id.putra.wms.module.inbound.dto.ReceivingDto;

public interface ReceivingOrderService {
    ReceivingDto create(ReceivingDto dto);

    ReceivingDto update(ReceivingDto dto);

    Boolean delete(@NonNull Long id);

    ReceivingDto getById(@NonNull Long id, @NonNull Pageable pageable);

    Page<ReceivingDto> getAll(String search, @NonNull Pageable pageable);

    ReceivingDto patch(ReceivingDto body);
}
