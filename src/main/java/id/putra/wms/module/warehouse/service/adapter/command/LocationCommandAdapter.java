package id.putra.wms.module.warehouse.service.adapter.command;

import java.util.List;

import id.putra.wms.module.warehouse.dto.LocationDto;

public interface LocationCommandAdapter {
    void add(List<LocationDto> dtos);

    void update(List<LocationDto> dtos);

    void delete(List<LocationDto> dtos);
}
