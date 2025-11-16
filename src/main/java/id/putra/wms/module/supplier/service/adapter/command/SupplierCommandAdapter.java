package id.putra.wms.module.supplier.service.adapter.command;

import java.util.List;

import id.putra.wms.module.supplier.dto.SupplierDto;

public interface SupplierCommandAdapter {
    List<SupplierDto> create(List<SupplierDto> dto);

    SupplierDto update(String id, SupplierDto dto);

    void delete(String id);
}
