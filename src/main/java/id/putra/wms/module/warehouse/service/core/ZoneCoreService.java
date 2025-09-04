package id.putra.wms.module.warehouse.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.ZoneDto;

public interface ZoneCoreService {
    Page<ZoneDto> getZones(ZoneDto dto, Pageable pageable);

    ZoneDto getZone(ZoneDto zoneDto);

    void add(List<ZoneDto> dtos);

    void update(List<ZoneDto> dtos);

    void delete(List<ZoneDto> dtos);
}
