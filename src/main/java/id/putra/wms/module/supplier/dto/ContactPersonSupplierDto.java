package id.putra.wms.module.supplier.dto;

import id.putra.wms.shared.base.dto.BaseDto;
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
public class ContactPersonSupplierDto extends BaseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long quantity;
}
