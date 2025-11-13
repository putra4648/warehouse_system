package id.putra.wms.module.outbound.dto;

import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;

@Data
public class SalesOrderLineDto {
    private Long id;
    private ProductDto product;
    private SalesOrderDto salesOrder;
}
