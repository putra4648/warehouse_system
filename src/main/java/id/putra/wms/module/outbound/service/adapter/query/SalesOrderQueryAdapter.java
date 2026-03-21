package id.putra.wms.module.outbound.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.outbound.dto.SalesOrderDto;

public interface SalesOrderQueryAdapter {
    SalesOrderDto getById(Long id);

    Page<SalesOrderDto> getAll(String search, Pageable pageable);
}
