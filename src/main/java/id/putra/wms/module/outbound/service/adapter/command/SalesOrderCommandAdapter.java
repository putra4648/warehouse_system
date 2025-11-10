package id.putra.wms.module.outbound.service.adapter.command;

import java.util.List;

import id.putra.wms.module.outbound.dto.SalesOrderDto;

public interface SalesOrderCommandAdapter {
    void add(List<SalesOrderDto> dtos);

    void update(List<SalesOrderDto> dtos);

    void delete(List<SalesOrderDto> dtos);
}
