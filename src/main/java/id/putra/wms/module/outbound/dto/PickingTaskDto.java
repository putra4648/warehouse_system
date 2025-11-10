package id.putra.wms.module.outbound.dto;

import lombok.Data;

@Data
public class PickingTaskDto {
    private Long id;
    private SalesOrderLineDto salesOrderLine;
}
