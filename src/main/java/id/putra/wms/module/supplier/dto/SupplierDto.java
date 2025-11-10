package id.putra.wms.module.supplier.dto;

import java.util.List;

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
    private List<ProductSupplierDto> products;
    private List<ContactPersonSupplierDto> contactPersonSuppliers;
    // Add other fields as needed
}
