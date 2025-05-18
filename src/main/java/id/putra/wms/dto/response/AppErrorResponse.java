package id.putra.wms.dto.response;

import lombok.Data;

import java.time.Instant;

@Data
public class AppErrorResponse {
    private String message;
    private Instant occurredDate = Instant.now();
}
