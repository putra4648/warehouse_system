package id.putra.wms.module.supplier.dto;

import java.util.List;

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
public class SupplierDto extends BaseDto {
    private Long id;
    private String name;
    private String code;
    private Boolean isActive;
    private List<ProductSupplierDto> products;
    private List<ContactPersonSupplierDto> contactPersonSuppliers;
}
