package id.putra.wms.module.warehouse.service.adapter.query.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.mapper.ContactPersonWarehouseMapper;
import id.putra.wms.module.warehouse.model.repository.ContactPersonWarehouseRepository;
import id.putra.wms.module.warehouse.service.adapter.query.ContactPersonWarehouseQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactPersonnWarehouseQueryAdapterImpl implements ContactPersonWarehouseQueryAdapter {
    private final ContactPersonWarehouseRepository contactPersonWarehouseRepository;
    private final ContactPersonWarehouseMapper contactPersonWarehouseMapper;

    @Override
    public List<ContactPersonWarehouseDto> getContactsByWarehouse(WarehouseDto warehouseDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContactsByWarehouse'");
    }

}
