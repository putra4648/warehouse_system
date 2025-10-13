package id.putra.wms.module.supplier.service.core.impl;

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
    public SupplierDto getById(String id) {
        SupplierDto entity = supplierQueryAdapter.getById(id);
        return entity;
    }

    @Override
    public SupplierDto create(SupplierDto dto) {
        return supplierCommandAdapter.create(dto);
    }

    @Override
    public SupplierDto update(String id, SupplierDto dto) {
        return supplierCommandAdapter.update(id, dto);
    }

    @Override
    public void delete(String id) {
        supplierCommandAdapter.delete(id);
    }
}
