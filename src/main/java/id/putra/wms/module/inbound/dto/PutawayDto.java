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
public class PutawayDto {
    private Long id;
    private String status;
    private Long receivingLineId;
    private Long locationId;
}
