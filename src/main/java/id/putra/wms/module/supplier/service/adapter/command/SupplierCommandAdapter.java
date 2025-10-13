package id.putra.wms.module.supplier.service.adapter.command;

import id.putra.wms.module.supplier.dto.SupplierDto;

public interface SupplierCommandAdapter {
    SupplierDto create(SupplierDto dto);

    SupplierDto update(String id, SupplierDto dto);

    void delete(String id);
}
