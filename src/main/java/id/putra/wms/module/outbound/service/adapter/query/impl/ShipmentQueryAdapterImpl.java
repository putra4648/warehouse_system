package id.putra.wms.module.outbound.service.adapter.query.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.putra.wms.module.outbound.converter.ShipmentConverter;
import id.putra.wms.module.outbound.dto.ShipmentDto;
import id.putra.wms.module.outbound.model.repository.ShipmentRepository;
import id.putra.wms.module.outbound.service.adapter.query.ShipmentQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShipmentQueryAdapterImpl implements ShipmentQueryAdapter {
    private final ShipmentRepository shipmentRepository;

    @Override
    public ShipmentDto getById(Long id) {
        return shipmentRepository.findById(id).map(ShipmentConverter::toDto).orElse(null);
    }

    @Override
    public List<ShipmentDto> getAll(ShipmentDto filter) {
        return shipmentRepository.findAll().stream().map(ShipmentConverter::toDto).toList();
    }
}
