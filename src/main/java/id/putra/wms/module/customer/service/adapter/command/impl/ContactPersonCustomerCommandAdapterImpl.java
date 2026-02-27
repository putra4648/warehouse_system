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
        var saved = repository.save(java.util.Objects.requireNonNull(entity));
        return mapper.toDto(saved);
    }

    @Override
    public ContactPersonCustomerDto update(Long id, ContactPersonCustomerDto dto) {
        Long safeId = java.util.Objects.requireNonNull(id);
        var entity = repository.findById(safeId).orElseThrow();
        var saved = repository.save(java.util.Objects.requireNonNull(entity));
        return mapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        Long safeId = java.util.Objects.requireNonNull(id);
        repository.deleteById(safeId);
    }
}
