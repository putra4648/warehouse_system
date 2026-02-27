package id.putra.wms.module.customer.service.adapter.command;

import id.putra.wms.module.customer.dto.CustomerDto;

public interface CustomerCommandAdapter {
    CustomerDto create(CustomerDto dto);

    CustomerDto update(Long id, CustomerDto dto);

    void delete(Long id);
}
