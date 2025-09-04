package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.LocationDto;

public interface LocationQueryAdapter {
    Page<LocationDto> getLocations(LocationDto dto, Pageable pageable);

    LocationDto getLocation(LocationDto dto);
}
