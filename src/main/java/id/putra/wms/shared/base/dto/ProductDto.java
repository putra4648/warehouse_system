package id.putra.wms.shared.base.dto;

import java.util.List;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.dto.InventoryItemDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDto extends BaseDto {
    private String id;
    private String name;
    private String description;
    private Long minStock;
    private Long maxStock;
    private Boolean isActive;
    private CategoryDto category;
    private List<InventoryItemDto> inventories;
}
