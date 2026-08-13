package id.putra.wms.module.customer.service.adapter.command.impl;

import org.springframework.stereotype.Service;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.customer.converter.CustomerConverter;
import id.putra.wms.module.customer.model.repository.CustomerRepository;
import id.putra.wms.module.customer.service.adapter.command.CustomerCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerCommandAdapterImpl implements CustomerCommandAdapter {
    private final CustomerRepository repository;

    @Override
    public CustomerDto create(CustomerDto dto) {
        var entity = CustomerConverter.toEntity(dto);
        var saved = repository.save(java.util.Objects.requireNonNull(entity));
        return CustomerConverter.toDto(saved);
    }

    @Override
    public CustomerDto update(Long id, CustomerDto dto) {
        Long safeId = java.util.Objects.requireNonNull(id);
        var entity = repository.findById(safeId).orElseThrow();
        // CustomerConverter.updateEntityFromDto(dto, entity);
        var saved = repository.save(java.util.Objects.requireNonNull(entity));
        return CustomerConverter.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        Long safeId = java.util.Objects.requireNonNull(id);
        repository.deleteById(safeId);
    }
}
