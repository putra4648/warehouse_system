package id.putra.wms.module.warehouse.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.RackDto;

public interface RackCoreService {
    Page<RackDto> getRacks(RackDto dto, Pageable pageable);

    RackDto getRack(RackDto rackDto);

    void add(List<RackDto> dtos);

    void update(List<RackDto> dtos);

    void delete(List<RackDto> dtos);
}
