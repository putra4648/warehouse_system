package id.putra.wms.module.supplier.service.core.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.module.supplier.service.adapter.command.SupplierCommandAdapter;
import id.putra.wms.module.supplier.service.adapter.query.SupplierQueryAdapter;
import id.putra.wms.module.supplier.service.core.SupplierService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierCommandAdapter supplierCommandAdapter;
    private final SupplierQueryAdapter supplierQueryAdapter;

    @Override
    public Page<SupplierDto> getAll(Pageable pageable, String search) {
        // Implement search logic if needed
        return supplierQueryAdapter.getAll(pageable, search);
    }

    @Override
    public SupplierDto getById(Long id) {
        SupplierDto entity = supplierQueryAdapter.getById(id);
        return entity;
    }

    @Override
    public List<SupplierDto> create(List<SupplierDto> dto) {
        return supplierCommandAdapter.create(dto);
    }

    @Override
    public SupplierDto update(Long id, SupplierDto dto) {
        return supplierCommandAdapter.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        supplierCommandAdapter.delete(id);
    }
}
