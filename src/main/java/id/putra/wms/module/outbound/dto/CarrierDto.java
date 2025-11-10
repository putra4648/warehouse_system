package id.putra.wms.module.outbound.dto;

import java.util.List;

import lombok.Data;

@Data
public class CarrierDto {
    private Long id;
    private String name;
    private String serviceType;
    private List<ShipmentDto> shipments;
}
