package id.putra.wms.module.warehouse.service.adapter.command.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.warehouse.dto.ContactPersonWarehouseDto;
import id.putra.wms.module.warehouse.mapper.ContactPersonWarehouseMapper;
import id.putra.wms.module.warehouse.model.repository.ContactPersonWarehouseRepository;
import id.putra.wms.module.warehouse.service.adapter.command.ContactPersonWarehouseCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ContactPersonWarehouseCommandAdapterImpl implements ContactPersonWarehouseCommandAdapter {

    private final ContactPersonWarehouseRepository contactPersonWarehouseRepository;
    private final ContactPersonWarehouseMapper contactPersonWarehouseMapper;

    @Override
    public void add(List<ContactPersonWarehouseDto> dtos) {
        var entities = dtos.stream().map(contactPersonWarehouseMapper::toEntity).toList();
        contactPersonWarehouseRepository.saveAll(Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<ContactPersonWarehouseDto> dtos) {
        var entities = dtos.stream().map(contactPersonWarehouseMapper::toEntity).toList();
        contactPersonWarehouseRepository.saveAll(Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<ContactPersonWarehouseDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        contactPersonWarehouseRepository.deleteAllById(Objects.requireNonNull(ids));
    }

}
