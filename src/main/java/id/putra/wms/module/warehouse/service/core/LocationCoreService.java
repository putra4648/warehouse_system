package id.putra.wms.module.warehouse.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.LocationDto;

public interface LocationCoreService {

    Page<LocationDto> getLocations(LocationDto dto, Pageable pageable);

    LocationDto getLocation(LocationDto dto);

    void add(List<LocationDto> dtos);

    void update(List<LocationDto> dtos);

    void delete(List<LocationDto> dtos);
}
