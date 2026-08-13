package id.putra.wms.shared.base.dto;

import java.util.List;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
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
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProductDto extends BaseDto {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private Long minStock;
    private Long maxStock;
    private Long quantity;
    private Boolean isActive;
    private List<InventoryItemDto> inventories;
}
