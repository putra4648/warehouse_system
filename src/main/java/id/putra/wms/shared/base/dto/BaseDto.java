package id.putra.wms.shared.base.dto;

import java.time.LocalTime;

import lombok.Data;

@Data

public class BaseDto {
    private LocalTime createdDate;
    private String createdBy;
    private LocalTime updatedDate;
    private String updatedBy;
}
