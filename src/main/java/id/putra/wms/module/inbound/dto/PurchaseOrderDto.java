package id.putra.wms.module.inbound.dto;

import java.sql.Date;
import java.util.List;

import id.putra.wms.module.supplier.dto.SupplierDto;
import lombok.Data;

@Data
public class PurchaseOrderDto {
    private Long id;
    private String poNumber;
    private String status;
    private Date orderDate;

    
    private List<PurchaseOrderLineDto> purchaseOrderLines;
    private List<ReceivingDto> receivings;
    private SupplierDto supplier;
}
