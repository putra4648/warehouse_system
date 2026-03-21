package id.putra.wms.module.outbound.service.adapter.command.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.putra.wms.module.outbound.dto.ShipmentDto;
import id.putra.wms.module.outbound.mapper.ShipmentMapper;
import id.putra.wms.module.outbound.model.repository.ShipmentRepository;
import id.putra.wms.module.outbound.service.adapter.command.ShipmentCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ShipmentCommandAdapterImpl implements ShipmentCommandAdapter {
    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;

    @Override
    public void add(List<ShipmentDto> dtos) {
        var entities = dtos.stream().map(shipmentMapper::toEntity).toList();
        shipmentRepository.saveAll(entities);
    }

    @Override
    public void update(List<ShipmentDto> dtos) {
        var entities = dtos.stream().map(shipmentMapper::toEntity).toList();
        shipmentRepository.saveAll(entities);
    }

    @Override
    public void delete(List<ShipmentDto> dtos) {
        var ids = dtos.stream().map(ShipmentDto::getId).toList();
        shipmentRepository.deleteAllById(ids);
    }
}
