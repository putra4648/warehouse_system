package id.putra.wms.module.warehouse.dto;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationDto extends BaseDto {
    private String id;
    private String name;
    private String type;
    private Boolean isActive;
    private String binNumber;
    private RackDto rack;
}
