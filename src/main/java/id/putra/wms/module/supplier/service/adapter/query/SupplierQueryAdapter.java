package id.putra.wms.module.supplier.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.supplier.dto.SupplierDto;

public interface SupplierQueryAdapter {
    Page<SupplierDto> getAll(Pageable pageable, String search);

    SupplierDto getById(Long id);

}
