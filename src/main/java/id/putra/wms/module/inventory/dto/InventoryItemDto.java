package id.putra.wms.module.inventory.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InventoryItemDto extends BaseDto {
    private String id;
    private String name;
    private Long quantity;
    private ProductDto product;
}
