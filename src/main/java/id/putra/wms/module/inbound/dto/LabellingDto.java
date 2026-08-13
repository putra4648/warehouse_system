package id.putra.wms.module.inbound.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabellingDto {
    private Long id;
    private String labelContent;
    private String status;
    private Long receivingLineId;
}
