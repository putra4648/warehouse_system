package id.putra.wms.module.outbound.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SalesOrderLineDto {
    private Long id;
    private Long productId;
    private BigDecimal quantity;
    private Long salesOrderId;
}
