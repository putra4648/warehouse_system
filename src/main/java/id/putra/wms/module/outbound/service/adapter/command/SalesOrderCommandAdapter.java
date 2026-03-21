package id.putra.wms.module.outbound.service.adapter.command;

import id.putra.wms.module.outbound.dto.SalesOrderDto;

public interface SalesOrderCommandAdapter {
    SalesOrderDto add(SalesOrderDto dto);

    SalesOrderDto update(SalesOrderDto dto);

    Boolean delete(Long id);
}
