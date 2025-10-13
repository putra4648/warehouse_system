package id.putra.wms.module.supplier.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ContactPersonSupplierDto extends BaseDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private SupplierDto supplier;
}
