package id.putra.wms.module.outbound.dto;

import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;

@Data
public class SalesOrderLineDto {
    private Long id;
    private ProductDto product;
    /** parent sales order id to avoid circular nested DTOs */
    private Long salesOrderId;
    private Integer qty;
}
