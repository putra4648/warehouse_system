package id.putra.wms.module.warehouse.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import id.putra.wms.shared.base.dto.model.BaseDto;
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
    private List<RackDto> racks;
}
