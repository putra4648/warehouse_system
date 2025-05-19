package id.putra.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogDto {
    private String operation;
    private String action;
    private String oldData;
    private String newData;
    private Date occurredOn;
}
