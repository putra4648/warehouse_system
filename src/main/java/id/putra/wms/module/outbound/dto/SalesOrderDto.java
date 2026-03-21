package id.putra.wms.module.outbound.dto;

import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
public class SalesOrderDto {
    private Long id;
    private String soNumber;
    private Date orderDate;
    private String status;
    private Long customerId;
    private List<SalesOrderLineDto> salesOrderLines;
}
