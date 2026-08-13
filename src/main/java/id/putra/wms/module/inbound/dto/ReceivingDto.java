package id.putra.wms.module.inbound.dto;

import java.sql.Date;
import java.util.List;

import id.putra.wms.shared.base.dto.response.attribute.MetaAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceivingDto {
    private Long id;
    private String receivingNumber;
    private Date receivedDate;
    private String status;
    // private String receivedBy;
    private PurchaseOrderDto purchaseOrder;
    private List<ReceivingLineDto> receivingLines;
    private MetaAttribute meta;
}
