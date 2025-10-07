package id.putra.wms.module.customer.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerDto extends BaseDto {
    private String id;
    private String name;
    // Add other fields as needed
}
