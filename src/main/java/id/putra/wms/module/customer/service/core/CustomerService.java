package id.putra.wms.module.customer.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.customer.mapper.CustomerMapper;
import id.putra.wms.module.customer.model.repository.CustomerRepository;
import id.putra.wms.shared.base.entity.Customer;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<CustomerDto> getAll(String search, Pageable pageable) {
        Specification<Customer> spec = (root, query, cb) -> search == null || search.isEmpty() ? cb.conjunction()
                : cb.like(root.get("name"), "%" + search + "%");
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return repository.findAll(spec, safePageable).map(mapper::toDto);
    }

    public CustomerDto create(CustomerDto dto) {
        Customer entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(java.util.Objects.requireNonNull(entity)));
    }

    public CustomerDto update(Long id, CustomerDto dto) {
        Long safeId = java.util.Objects.requireNonNull(id);
        Customer entity = repository.findById(safeId).orElseThrow();
        // You may want to copy fields from dto to entity here
        entity.setName(dto.getName());
        // ... other fields
        return mapper.toDto(repository.save(java.util.Objects.requireNonNull(entity)));
    }

    public void delete(Long id) {
        Long safeId = java.util.Objects.requireNonNull(id);
        repository.deleteById(safeId);
    }
}
