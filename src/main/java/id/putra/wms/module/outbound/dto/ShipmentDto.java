package id.putra.wms.module.outbound.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentDto {
    private Long id;
    private String shipmentNumber;
    private Date shipDate;
    private String status;
    private Long packingId;
    private Long carrierId;
}
