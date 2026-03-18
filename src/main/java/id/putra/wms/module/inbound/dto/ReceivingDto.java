package id.putra.wms.module.inbound.dto;

import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
public class ReceivingDto {
    private Long id;
    private String receivingNumber;
    private Date receivedDate;
    private String status;
    private Long userId;
    private Long purchaseOrderId;
    private List<ReceivingLineDto> receivingLines;
}
