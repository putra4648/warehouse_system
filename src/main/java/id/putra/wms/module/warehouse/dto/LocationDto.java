package id.putra.wms.module.warehouse.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
