package id.putra.wms.module.outbound.dto;

import java.sql.Date;
import java.util.List;

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
public class SalesOrderDto {
    private Long id;
    private String soNumber;
    private Date orderDate;
    private String status;
    private Long customerId;
    private List<SalesOrderLineDto> salesOrderLines;
}
