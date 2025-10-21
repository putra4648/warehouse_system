package id.putra.wms.module.customer.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerDto extends BaseDto {
    private String id;
    private String code;
    private String name;
    private Boolean isActive;
    private ContactPersonCustomerDto contactPerson;
    // Add other fields as needed
}
