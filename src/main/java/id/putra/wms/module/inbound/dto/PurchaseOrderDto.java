package id.putra.wms.module.inbound.dto;

import java.sql.Date;
import java.util.List;

import id.putra.wms.shared.base.dto.response.attribute.MetaAttribute;
import id.putra.wms.shared.enums.OrderStatus;
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
public class PurchaseOrderDto {
    private Long id;
    private String poNumber;
    private OrderStatus status;
    private Date orderDate;
    private Long supplierId;
    private List<PurchaseOrderLineDto> purchaseOrderLines;
    private MetaAttribute meta;
}
