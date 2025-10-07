package id.putra.wms.module.transaction.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionItemDto extends BaseDto {
    private String id;
    private String transactionId;
    // Add other fields as needed
}
