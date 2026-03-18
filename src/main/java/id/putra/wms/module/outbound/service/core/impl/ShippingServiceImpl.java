package id.putra.wms.module.outbound.service.core.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import id.putra.wms.module.outbound.dto.ShipmentDto;
import id.putra.wms.module.outbound.service.adapter.command.ShipmentCommandAdapter;
import id.putra.wms.module.outbound.service.adapter.query.ShipmentQueryAdapter;
import id.putra.wms.module.outbound.service.core.ShippingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {
    private final ShipmentCommandAdapter shipmentCommandAdapter;
    private final ShipmentQueryAdapter shipmentQueryAdapter;

    @Override
    public ShipmentDto create(ShipmentDto dto) {
        shipmentCommandAdapter.add(List.of(dto));
        return dto;
    }

    @Override
    public ShipmentDto update(ShipmentDto dto) {
        shipmentCommandAdapter.update(List.of(dto));
        return dto;
    }

    @Override
    public Boolean delete(Long id) {
        ShipmentDto dto = new ShipmentDto();
        dto.setId(id);
        shipmentCommandAdapter.delete(List.of(dto));
        return true;
    }

    @Override
    public ShipmentDto getById(Long id) {
        return shipmentQueryAdapter.getById(id);
    }

    @Override
    public List<ShipmentDto> getAll(ShipmentDto filter) {
        return shipmentQueryAdapter.getAll(filter);
    }
}
