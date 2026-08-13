package id.putra.wms.module.inbound.dto;

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
public class QualityInspectionDto {
    private Long id;
    private String comments;
    private String status;
    private Long receivingLineId;
    private Long inspectorId;
}
