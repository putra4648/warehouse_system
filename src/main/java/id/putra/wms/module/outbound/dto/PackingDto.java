package id.putra.wms.module.outbound.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PackingDto {
    private Long id;
    private String packageNumber;
    private BigDecimal weight;
    private String dimension;

    
    private List<ShipmentDto> shipments;
}
