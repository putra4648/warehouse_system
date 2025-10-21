package id.putra.wms.module.inventory.dto;

import java.util.List;

import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryDto {
    private String id;
    private String name;
    private List<ProductDto> products;
    // Add other fields as needed
}
