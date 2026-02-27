package id.putra.wms.module.warehouse.dto;

import java.util.List;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
public class RackDto extends BaseDto {
        private Long id;
        private String name;
        private Long rows;
        private Long cols;
        private Boolean isActive;
        private List<LocationDto> locations;
        private ZoneDto zone;
}
