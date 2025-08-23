package id.putra.wms.module.warehouse.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ZoneDto(
        String code,
        String name,
        Boolean isActive,
        String type,
        List<RackDto> racks) {
}
