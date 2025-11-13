package id.putra.wms.module.outbound.service.core;

import java.util.List;

import id.putra.wms.module.outbound.dto.SalesOrderDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface SalesOrderService {
    ResponseData<SalesOrderDto> create(SalesOrderDto dto);
    ResponseData<SalesOrderDto> update(SalesOrderDto dto);
    ResponseData<Boolean> delete(Long id);
    ResponseData<SalesOrderDto> getById(Long id);
    ResponseData<List<SalesOrderDto>> getAll(SalesOrderDto filter);
}
