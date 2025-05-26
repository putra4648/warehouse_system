package id.putra.wms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AuditLogDto {
    private String operation;
    private String action;
    private String oldData;
    private String newData;
    private Date occurredOn;
}
