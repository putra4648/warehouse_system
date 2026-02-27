package id.putra.wms.module.customer.service.adapter.command;

import id.putra.wms.module.customer.dto.ContactPersonCustomerDto;

public interface ContactPersonCustomerCommandAdapter {
    ContactPersonCustomerDto create(ContactPersonCustomerDto dto);

    ContactPersonCustomerDto update(Long id, ContactPersonCustomerDto dto);

    void delete(Long id);
}
