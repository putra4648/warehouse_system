package id.putra.wms.shared.base.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BaseDto {
    private ZonedDateTime createdDate;
    private String createdBy;
    private ZonedDateTime updatedDate;
    private String updatedBy;
}
