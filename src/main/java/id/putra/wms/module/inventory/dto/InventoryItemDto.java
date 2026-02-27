package id.putra.wms.module.inventory.dto;

import java.time.ZonedDateTime;

import id.putra.wms.shared.base.dto.BaseDto;
import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class InventoryItemDto extends BaseDto {
    private Long id;
    private String name;
    private Long quantity;
    private ZonedDateTime expiredDate;
    private Long reservedQty;
    private String lotNumber;
    private ProductDto product;
    private String status;
    private String serialNumber;
}
