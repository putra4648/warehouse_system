package id.putra.wms.module.customer.dto;

import java.util.List;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ContactPersonCustomerDto extends BaseDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private List<CustomerDto> customers;
    // Add other fields as needed
}
