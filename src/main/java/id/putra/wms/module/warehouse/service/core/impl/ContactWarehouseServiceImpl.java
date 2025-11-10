package id.putra.wms.module.warehouse.service.core.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.service.adapter.command.ContactPersonWarehouseCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.ContactPersonWarehouseQueryAdapter;
import id.putra.wms.module.warehouse.service.core.ContactPersonWarehouseCoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactWarehouseServiceImpl implements ContactPersonWarehouseCoreService {
    private final ContactPersonWarehouseQueryAdapter contactPersonWarehouseQueryAdapter;
    private final ContactPersonWarehouseCommandAdapter contactPersonCommandAdapter;

    @Override
    public Page<ContactPersonWarehouseDto> getContacts(ContactPersonWarehouseDto dto, Pageable pageable) {
        return contactPersonWarehouseQueryAdapter.getContacts(dto, pageable);
    }

    @Override
    public ContactPersonWarehouseDto getContact(ContactPersonWarehouseDto dto) {
        return contactPersonWarehouseQueryAdapter.getContact(dto);
    }

    @Override
    public void add(List<ContactPersonWarehouseDto> dtos) {
        contactPersonCommandAdapter.add(dtos);
    }

    @Override
    public void update(List<ContactPersonWarehouseDto> dtos) {
        contactPersonCommandAdapter.update(dtos);
    }

    @Override
    public void delete(List<ContactPersonWarehouseDto> dtos) {
        contactPersonCommandAdapter.delete(dtos);
    }

}
