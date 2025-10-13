package id.putra.wms.module.inventory.dto;

import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryDto {
    private String id;
    private String name;
    private ProductDto product;
    // Add other fields as needed
}
