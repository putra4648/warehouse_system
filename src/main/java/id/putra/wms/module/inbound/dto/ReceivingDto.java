package id.putra.wms.module.inbound.dto;

import java.sql.Date;
import java.util.List;

import id.putra.wms.module.inbound.model.entity.PurchaseOrder;
import id.putra.wms.shared.base.entity.User;
import lombok.Data;

@Data
public class ReceivingDto {
    private Long id;
    private String receivingNumber;
    private Date receivedDate;
    private String status;
    private User user;
    private PurchaseOrder purchaseOrder;
    private List<ReceivingLineDto> receivingLines;
}
