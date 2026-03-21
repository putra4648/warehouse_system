package id.putra.wms.module.inbound.dto;

import lombok.Data;

@Data
public class PutawayDto {
    private Long id;
    private String status;
    private Long receivingLineId;
    private Long locationId;
}
