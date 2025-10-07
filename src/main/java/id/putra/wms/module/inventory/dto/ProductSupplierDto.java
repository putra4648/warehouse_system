package id.putra.wms.module.inventory.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSupplierDto extends BaseDto {
    private String id;
    private String productId;
    private String supplierId;
    // Add other fields as needed
}
