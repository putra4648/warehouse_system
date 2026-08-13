package id.putra.wms.shared.base.dto;

import java.time.LocalTime;

import lombok.Data;

@Data
public class BaseDto {
    private LocalTime createdAt;
    private String createdBy;
    private LocalTime updatedAt;
    private String updatedBy;
}
