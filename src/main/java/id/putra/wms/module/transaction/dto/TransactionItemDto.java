package id.putra.wms.module.transaction.dto;

import java.math.BigDecimal;

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
public class TransactionItemDto extends BaseDto {
    private Long id;

    private Long transactionId;
    private Long productId;
    private Long sourceInventoryItemId;
    private Long destInventoryItemId;

    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String lotNumber;
    private String serialNumber;
    private String notes;
}
