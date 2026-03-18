package id.putra.wms.module.outbound.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class ShipmentDto {
    private Long id;
    private String shipmentNumber;
    private Date shipDate;
    private String status;
    private Long packingId;
    private Long carrierId;
}
