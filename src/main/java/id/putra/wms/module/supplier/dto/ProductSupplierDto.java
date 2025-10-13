package id.putra.wms.module.supplier.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSupplierDto extends BaseDto {
    private ProductSupplierIdDto id;
    private ProductDto product;
    private SupplierDto supplier;
    // Add other fields as needed
}
