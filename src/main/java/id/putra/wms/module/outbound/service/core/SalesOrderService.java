package id.putra.wms.module.outbound.service.core;

import java.util.List;

import id.putra.wms.module.outbound.dto.SalesOrderDto;

public interface SalesOrderService {
    SalesOrderDto create(SalesOrderDto dto);
    SalesOrderDto update(SalesOrderDto dto);
    Boolean delete(Long id);
    SalesOrderDto getById(Long id);
    List<SalesOrderDto> getAll(SalesOrderDto filter);
}
