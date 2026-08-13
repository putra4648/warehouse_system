package id.putra.wms.module.outbound.dto;

import java.math.BigDecimal;

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
public class SalesOrderLineDto {
    private Long id;
    private Long productId;
    private BigDecimal quantity;
    private Long salesOrderId;
}
