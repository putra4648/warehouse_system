package id.putra.wms.module.warehouse.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;

public interface ContactPersonWarehouseCoreService {
    Page<ContactPersonWarehouseDto> getContacts(ContactPersonWarehouseDto dto, Pageable pageable);

    ContactPersonWarehouseDto getContact(ContactPersonWarehouseDto dto);

    void add(List<ContactPersonWarehouseDto> dtos);

    void update(List<ContactPersonWarehouseDto> dtos);

    void delete(List<ContactPersonWarehouseDto> dtos);
}
