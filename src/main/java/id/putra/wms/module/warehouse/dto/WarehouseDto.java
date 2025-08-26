package id.putra.wms.module.warehouse.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Builder(toBuilder = true)
public class WarehouseDto {
    private String id;
    private String name;
    private Boolean isActive;
    private String location;
    private Double area;
    private Integer total;
    private List<ZoneDto> zones;
}
