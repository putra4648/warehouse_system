package id.putra.wms.module.warehouse.service.adapter.command;

import java.util.List;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;

public interface ContactPersonWarehouseCommandAdapter {
    void add(List<ContactPersonWarehouseDto> dtos);

    void update(List<ContactPersonWarehouseDto> dtos);

    void delete(List<ContactPersonWarehouseDto> dtos);
}
