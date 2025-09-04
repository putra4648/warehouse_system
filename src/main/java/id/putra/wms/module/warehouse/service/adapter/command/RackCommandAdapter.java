package id.putra.wms.module.warehouse.service.adapter.command;

import java.util.List;

import id.putra.wms.module.warehouse.dto.RackDto;

public interface RackCommandAdapter {
    void add(List<RackDto> dtos);

    void update(List<RackDto> dtos);

    void delete(List<RackDto> dtos);
}
