package id.putra.wms.module.transaction.dto;

import java.math.BigDecimal;

import id.putra.wms.module.inventory.dto.InventoryItemDto;
import id.putra.wms.shared.base.dto.BaseDto;
import id.putra.wms.shared.base.dto.ProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionItemDto extends BaseDto {
    private String id;

    private TransactionDto transaction;
    private ProductDto product;
    private InventoryItemDto sourceInventoryItem;
    private InventoryItemDto destInventoryItem;

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String lotNumber;
    private String serialNumber;
    private String notes;
}
