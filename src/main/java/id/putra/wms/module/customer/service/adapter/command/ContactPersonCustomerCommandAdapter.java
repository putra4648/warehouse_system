package id.putra.wms.module.customer.service.adapter.command;

import id.putra.wms.module.customer.dto.ContactPersonCustomerDto;

public interface ContactPersonCustomerCommandAdapter {
    ContactPersonCustomerDto create(ContactPersonCustomerDto dto);

    ContactPersonCustomerDto update(String id, ContactPersonCustomerDto dto);

    void delete(String id);
}
