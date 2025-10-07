package id.putra.wms.module.customer.service.adapter.command.impl;

import org.springframework.stereotype.Service;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.customer.mapper.CustomerMapper;
import id.putra.wms.module.customer.model.repository.CustomerRepository;
import id.putra.wms.module.customer.service.adapter.command.CustomerCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerCommandAdapterImpl implements CustomerCommandAdapter {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public CustomerDto create(CustomerDto dto) {
        var entity = mapper.toEntity(dto);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public CustomerDto update(String id, CustomerDto dto) {
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
