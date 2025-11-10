package id.putra.wms.module.inbound.dto;

import lombok.Data;

@Data
public class QualityInspectionDto {
    private Long id;
    private String result;
    private String remarks;

    private ReceivingLineDto receivingLine;

}
