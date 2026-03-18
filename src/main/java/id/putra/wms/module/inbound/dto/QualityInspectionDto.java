package id.putra.wms.module.inbound.dto;

import lombok.Data;

@Data
public class QualityInspectionDto {
    private Long id;
    private String comments;
    private String status;
    private Long receivingLineId;
    private Long inspectorId;
}
