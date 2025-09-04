package id.putra.wms.module.warehouse.service.adapter.command;

import java.util.List;

import id.putra.wms.module.warehouse.dto.ZoneDto;

public interface ZoneCommandAdapter {
    void add(List<ZoneDto> dtos);

    void update(List<ZoneDto> dtos);

    void delete(List<ZoneDto> dtos);
}
