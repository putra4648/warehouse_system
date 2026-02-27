package id.putra.wms.module.customer.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.customer.mapper.CustomerMapper;
import id.putra.wms.module.customer.model.repository.CustomerRepository;
import id.putra.wms.module.customer.service.adapter.query.CustomerQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerQueryAdapterImpl implements CustomerQueryAdapter {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public Page<CustomerDto> getAll(Pageable pageable, String search) {
        // Implement search logic here
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return repository.findAll(safePageable).map(mapper::toDto);
    }

    @Override
    public CustomerDto getById(Long id) {
        Long safeId = java.util.Objects.requireNonNull(id);
        var entity = repository.findById(safeId).orElseThrow();
        return mapper.toDto(entity);
    }
}
