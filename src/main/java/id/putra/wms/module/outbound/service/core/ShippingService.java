package id.putra.wms.module.outbound.service.core;

import java.util.List;

import id.putra.wms.module.outbound.dto.ShipmentDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface ShippingService {
    ResponseData<ShipmentDto> createShipment(ShipmentDto dto);
    ResponseData<ShipmentDto> updateShipment(ShipmentDto dto);
    ResponseData<Boolean> deleteShipment(Long id);
    ResponseData<ShipmentDto> getShipmentById(Long id);
    ResponseData<List<ShipmentDto>> getAllShipments(ShipmentDto filter);
}
