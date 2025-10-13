package id.putra.wms.module.supplier.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierDto extends BaseDto {
    private String id;
    private String name;
    private String code;
    private Boolean isActive;
    private ProductSupplierDto product;
    // Add other fields as needed
}
