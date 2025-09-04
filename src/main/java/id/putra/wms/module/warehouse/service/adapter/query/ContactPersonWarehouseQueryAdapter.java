package id.putra.wms.module.warehouse.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;

public interface ContactPersonWarehouseQueryAdapter {
    Page<ContactPersonWarehouseDto> getContacts(ContactPersonWarehouseDto dto, Pageable pageable);

    ContactPersonWarehouseDto getContact(ContactPersonWarehouseDto dto);
}
