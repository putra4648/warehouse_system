package id.putra.wms.module.user.dto.request;

import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class AuditLogDto {

    private String operation;

    private String action;

    private String oldData;

    private String newData;

    private ZonedDateTime occurredOn;

}
