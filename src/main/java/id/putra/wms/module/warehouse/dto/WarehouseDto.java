package id.putra.wms.module.warehouse.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record WarehouseDto(
                String id,
                String name,
                Boolean isActive,
                String location,
                Double area,
                Integer total,
                List<ZoneDto> zones) {
}
