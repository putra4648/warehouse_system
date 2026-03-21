package id.putra.wms.module.inbound.dto;

import lombok.Data;

@Data
public class ReturnDto {
    private Long id;
    private String reason;
    private String status;
    private Long receivingLineId;
}
