package id.putra.wms.module.transaction.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
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
public class TransactionDto extends BaseDto {
    private Long id;

    private String txnNumber;
    private String txnType;
    private ZonedDateTime txnDate;
    private String sourceLocId;
    private String destLocId;
    private String refNumber;
    private String refType;
    private String status;
    private String notes;
    private BigDecimal totalAmount;
    private Long quantity;

    private Long supplierId;
    private Long customerId;
    private List<TransactionItemDto> items;
}
