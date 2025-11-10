package id.putra.wms.module.inbound.dto;

import java.sql.Date;
import java.util.List;

import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import lombok.Data;

@Data
public class ReceivingDto {
    private Long id;
    private String receivingNumber;
    private Date receivedDate;
    private String status;
    private PurchaseOrder purchaseOrder;
    private List<ReceivingLineDto> receivingLines;
}
