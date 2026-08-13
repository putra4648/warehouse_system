package id.putra.wms.module.supplier.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import id.putra.wms.shared.base.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class ProductSupplierDto extends BaseDto {
    private ProductSupplierIdDto id;
    private ProductDto product;
}
