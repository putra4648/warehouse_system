package id.putra.wms.module.customer.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.customer.mapper.CustomerMapper;
import id.putra.wms.module.customer.model.repository.CustomerRepository;
import id.putra.wms.module.customer.model.entity.ContactPersonCustomer;
import id.putra.wms.shared.base.entity.Customer;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;

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

    public CustomerDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND));
    }

    public List<CustomerDto> create(List<CustomerDto> dtos) {
        List<Customer> entities = dtos.stream().map(mapper::toEntity).collect(Collectors.toList());
        return repository.saveAll(entities).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public CustomerDto update(Long id, CustomerDto dto) {
        Long safeId = java.util.Objects.requireNonNull(id);
        Customer entity = repository.findById(safeId).orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND));
        
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setIsActive(dto.getIsActive());
        
        // Handle contact person update mapping if needed
        if (dto.getContactPerson() != null) {
            if (entity.getContactPerson() == null) {
                entity.setContactPerson(new ContactPersonCustomer());
            }
            entity.getContactPerson().setName(dto.getContactPerson().getName());
            entity.getContactPerson().setEmail(dto.getContactPerson().getEmail());
            entity.getContactPerson().setPhone(dto.getContactPerson().getPhone());
            entity.getContactPerson().setAddress(dto.getContactPerson().getAddress());
        }

        return mapper.toDto(repository.save(entity));
    }

    public void delete(Long id) {
        Long safeId = java.util.Objects.requireNonNull(id);
        repository.deleteById(safeId);
    }
}
