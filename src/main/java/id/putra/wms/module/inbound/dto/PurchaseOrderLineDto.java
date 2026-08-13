package id.putra.wms.module.inbound.dto;

import java.math.BigDecimal;

import id.putra.wms.shared.base.dto.ProductDto;
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
public class PurchaseOrderLineDto {
    private Long id;
    private ProductDto product;
    private Integer quantity;
    private BigDecimal price;
}
