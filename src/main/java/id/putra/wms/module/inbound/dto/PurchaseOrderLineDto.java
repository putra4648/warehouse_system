package id.putra.wms.module.inbound.dto;

import java.math.BigDecimal;

import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;

@Data
public class PurchaseOrderLineDto {
    private Long id;
    private ProductDto product;
    private Integer quantity;
    private BigDecimal price;
}
