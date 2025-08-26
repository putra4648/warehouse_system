package id.putra.wms.module.warehouse.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder(toBuilder = true)
@Getter
public class ZoneDto {
        private String id;
        private String code;
        private String name;
        private Boolean isActive;
        private String type;
        private WarehouseDto warehouse;
        private List<RackDto> racks;
}
