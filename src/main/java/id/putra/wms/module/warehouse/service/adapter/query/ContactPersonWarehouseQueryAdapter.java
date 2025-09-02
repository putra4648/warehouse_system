package id.putra.wms.module.warehouse.service.adapter.query;

import java.util.List;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;

public interface ContactPersonWarehouseQueryAdapter {
    List<ContactPersonWarehouseDto> getContactsByWarehouse(WarehouseDto warehouseDto);
}
