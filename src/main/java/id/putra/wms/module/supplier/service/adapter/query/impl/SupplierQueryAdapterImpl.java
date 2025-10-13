package id.putra.wms.module.supplier.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.module.supplier.mapper.SupplierMapper;
import id.putra.wms.module.supplier.model.repository.SupplierRepository;
import id.putra.wms.module.supplier.service.adapter.query.SupplierQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierQueryAdapterImpl implements SupplierQueryAdapter {
    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    @Override
    public Page<SupplierDto> getAll(Pageable pageable, String search) {
        // Implement search logic here
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public SupplierDto getById(String id) {
        var entity = repository.findById(id).orElseThrow();
        return mapper.toDto(entity);
    }
}
