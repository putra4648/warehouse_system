package id.putra.wms.module.warehouse.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.mapper.ContactPersonWarehouseMapper;
import id.putra.wms.module.warehouse.model.entity.ContactPersonWarehouse;
import id.putra.wms.module.warehouse.model.repository.ContactPersonWarehouseRepository;
import id.putra.wms.module.warehouse.service.adapter.query.ContactPersonWarehouseQueryAdapter;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactPersonnWarehouseQueryAdapterImpl implements ContactPersonWarehouseQueryAdapter {
    private final ContactPersonWarehouseRepository contactPersonWarehouseRepository;
    private final ContactPersonWarehouseMapper contactPersonWarehouseMapper;

    @Override
    public Page<ContactPersonWarehouseDto> getContacts(ContactPersonWarehouseDto dto, Pageable pageable) {
        Specification<ContactPersonWarehouse> specs = (root, criteria, builder) -> {
            Predicate name = builder.like(root.get("name"),
                    "%" + (StringUtils.hasText(dto.getName()) ? dto.getName() : "") + "%");
            return builder.and(name);
        };
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return contactPersonWarehouseRepository.findAll(specs, safePageable).map(contactPersonWarehouseMapper::toDto);
    }

    @Override
    public ContactPersonWarehouseDto getContact(ContactPersonWarehouseDto dto) {
        String safeId = java.util.Objects.requireNonNull(dto.getId());
        return contactPersonWarehouseRepository.findById(safeId).map(contactPersonWarehouseMapper::toDto)
                .orElse(null);
    }

}
