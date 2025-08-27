package id.putra.wms.module.warehouse.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import id.putra.wms.shared.base.dto.model.BaseDto;
import lombok.Builder;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder(toBuilder = true)
@Getter
public class RackDto extends BaseDto {
        private String id;
        private String name;
        private Long rows;
        private Long cols;
        private List<LocationDto> locations;
        private ZoneDto zone;
}
