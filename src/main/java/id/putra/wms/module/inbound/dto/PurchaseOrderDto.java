package id.putra.wms.module.inbound.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class PurchaseOrderDto {
    private Long id;
    private String poNumber;
    private String status;
    private Date orderDate;
    private Long supplierId;
    private List<PurchaseOrderLineDto> purchaseOrderLines;
}
