package id.putra.wms.module.supplier.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.supplier.dto.SupplierDto;

public interface SupplierService {

    Page<SupplierDto> getAll(Pageable pageable, String search);

    SupplierDto getById(String id);

    SupplierDto create(SupplierDto dto);

    SupplierDto update(String id, SupplierDto dto);

    void delete(String id);
}
