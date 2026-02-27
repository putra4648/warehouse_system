package id.putra.wms.module.customer.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.customer.dto.ContactPersonCustomerDto;

public interface ContactPersonCustomerQueryAdapter {
    Page<ContactPersonCustomerDto> getAll(Pageable pageable, String search);

    ContactPersonCustomerDto getById(Long id);
}
