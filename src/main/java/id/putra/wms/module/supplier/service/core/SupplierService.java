package id.putra.wms.module.supplier.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.supplier.dto.SupplierDto;

public interface SupplierService {

    Page<SupplierDto> getAll(Pageable pageable, String search);

    SupplierDto getById(String id);

    List<SupplierDto> create(List<SupplierDto> dto);

    SupplierDto update(String id, SupplierDto dto);

    void delete(String id);
}
