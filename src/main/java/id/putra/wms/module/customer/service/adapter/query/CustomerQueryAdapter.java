package id.putra.wms.module.customer.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.customer.dto.CustomerDto;

public interface CustomerQueryAdapter {
    Page<CustomerDto> getAll(Pageable pageable, String search);

    CustomerDto getById(Long id);
}
