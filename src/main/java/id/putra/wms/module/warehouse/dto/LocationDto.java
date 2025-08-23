package id.putra.wms.module.warehouse.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LocationDto(
                String id,
                String name,
                String type,
                Boolean isActive,
                String binNumber) {

}
