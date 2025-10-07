package id.putra.wms.module.customer.service.adapter.command.impl;

import org.springframework.stereotype.Service;

import id.putra.wms.module.customer.dto.ContactPersonCustomerDto;
import id.putra.wms.module.customer.mapper.ContactPersonCustomerMapper;
import id.putra.wms.module.customer.model.repository.ContactPersonCustomerRepository;
import id.putra.wms.module.customer.service.adapter.command.ContactPersonCustomerCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactPersonCustomerCommandAdapterImpl implements ContactPersonCustomerCommandAdapter {
    private final ContactPersonCustomerRepository repository;
    private final ContactPersonCustomerMapper mapper;

    @Override
    public ContactPersonCustomerDto create(ContactPersonCustomerDto dto) {
        var entity = mapper.toEntity(dto);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public ContactPersonCustomerDto update(String id, ContactPersonCustomerDto dto) {
        var entity = repository.findById(id).orElseThrow();
        // mapper.updateEntityFromDto(dto, entity);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
