package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.RackDto;

public interface RackQueryAdapter {
    Page<RackDto> getRacks(RackDto dto, Pageable pageable);

    RackDto getRackById(RackDto rackDto);

}
