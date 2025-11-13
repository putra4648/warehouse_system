package id.putra.wms.module.outbound.dto;

import java.sql.Date;
import java.util.List;

import id.putra.wms.shared.base.entity.Customer;
import lombok.Data;

@Data
public class SalesOrderDto {
    private Long id;
    private String soNumber;
    private Date orderDate;
    private String status;
    private Customer customer;
    private List<SalesOrderLineDto> salesOrderLines;
}
