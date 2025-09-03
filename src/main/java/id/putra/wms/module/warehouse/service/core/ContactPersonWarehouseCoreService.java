package id.putra.wms.module.warehouse.service.core;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;

public interface ContactPersonWarehouseCoreService {
    Page<ContactPersonWarehouseDto> getContacts(ContactPersonWarehouseDto dto, Pageable pageable);
}
