package id.putra.wms.module.supplier.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.supplier.converter.SupplierConverter;
import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.module.supplier.model.repository.SupplierRepository;
import id.putra.wms.module.supplier.service.adapter.command.SupplierCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplierCommandAdapterImpl implements SupplierCommandAdapter {
    private final SupplierRepository repository;

    @Override
    public List<SupplierDto> create(List<SupplierDto> dto) {
        var entity = dto.stream().map(SupplierConverter::toEntity).toList();
        var saved = repository.saveAll(java.util.Objects.requireNonNull(entity));
        return SupplierConverter.toDtos(saved);
    }

    @Override
    public SupplierDto update(Long id, SupplierDto dto) {
        Long safeId = java.util.Objects.requireNonNull(id);
        var entity = repository.findById(safeId).orElseThrow();
        SupplierConverter.updateEntityFromDto(dto, entity);
        var saved = repository.save(java.util.Objects.requireNonNull(entity));
        return SupplierConverter.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        Long safeId = java.util.Objects.requireNonNull(id);
        repository.deleteById(safeId);
    }
}
