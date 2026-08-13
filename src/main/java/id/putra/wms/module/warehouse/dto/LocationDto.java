package id.putra.wms.module.warehouse.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class LocationDto extends BaseDto {
    private Long id;
    private String name;
    private String type;
    private Boolean isActive;
    private String binNumber;
    /** parent rack id to avoid circular nested DTOs */
    private String rackId;
}
