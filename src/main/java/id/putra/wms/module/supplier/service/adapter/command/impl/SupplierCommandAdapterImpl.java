package id.putra.wms.module.supplier.service.adapter.command.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.module.supplier.mapper.SupplierMapper;
import id.putra.wms.module.supplier.model.repository.SupplierRepository;
import id.putra.wms.module.supplier.service.adapter.command.SupplierCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplierCommandAdapterImpl implements SupplierCommandAdapter {
    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    @Override
    public SupplierDto create(SupplierDto dto) {
        var entity = mapper.toEntity(dto);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public SupplierDto update(String id, SupplierDto dto) {
        var entity = repository.findById(id).orElseThrow();
        // mapper.updateEntityFromDto(dto, entity); // If you have an update method
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
