package id.putra.wms.module.outbound.service.adapter.query;

import java.util.List;
import id.putra.wms.module.outbound.dto.SalesOrderDto;

public interface SalesOrderQueryAdapter {
    SalesOrderDto getById(Long id);
    List<SalesOrderDto> getAll(SalesOrderDto filter);
}
