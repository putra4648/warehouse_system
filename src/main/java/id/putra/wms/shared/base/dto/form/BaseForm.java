package id.putra.wms.shared.base.dto.form;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class BaseForm {
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
