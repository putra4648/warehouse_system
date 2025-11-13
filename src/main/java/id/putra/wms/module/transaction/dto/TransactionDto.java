package id.putra.wms.module.transaction.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import id.putra.wms.module.customer.dto.CustomerDto;
import id.putra.wms.module.supplier.dto.SupplierDto;
import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionDto extends BaseDto {
    private String id;

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

    private SupplierDto supplier;
    private CustomerDto customer;
    private List<TransactionItemDto> items;
    // Add other fields as needed
}
