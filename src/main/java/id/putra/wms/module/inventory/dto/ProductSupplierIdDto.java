package id.putra.wms.module.inventory.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSupplierIdDto extends BaseDto {
    private String id;
    // Add other fields as needed
}
