package id.putra.wms.module.warehouse.dto;

import java.util.List;

import id.putra.wms.shared.base.dto.BaseDto;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class RackDto extends BaseDto {
        private String id;
        private String name;
        private Long rows;
        private Long cols;
        private Boolean isActive;
        private List<LocationDto> locations;
                /** parent zone id to avoid circular nested DTOs */
                private String zoneId;
}
