package id.putra.wms.module.customer.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.customer.dto.ContactPersonCustomerDto;
import id.putra.wms.module.customer.mapper.ContactPersonCustomerMapper;
import id.putra.wms.module.customer.model.repository.ContactPersonCustomerRepository;
import id.putra.wms.module.customer.service.adapter.query.ContactPersonCustomerQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactPersonCustomerQueryAdapterImpl implements ContactPersonCustomerQueryAdapter {
    private final ContactPersonCustomerRepository repository;
    private final ContactPersonCustomerMapper mapper;

    @Override
    public Page<ContactPersonCustomerDto> getAll(Pageable pageable, String search) {
        // Implement search logic here
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return repository.findAll(safePageable).map(mapper::toDto);
    }

    @Override
    public ContactPersonCustomerDto getById(String id) {
        String safeId = java.util.Objects.requireNonNull(id);
        var entity = repository.findById(safeId).orElseThrow();
        return mapper.toDto(entity);
    }
}
