package id.putra.wms.module.inbound.dto;

import lombok.Data;

@Data
public class LabellingDto {
    private Long id;
    private String labelContent;
    private String status;
    private Long receivingLineId;
}
