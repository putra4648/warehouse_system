package id.putra.wms.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog extends BaseEntity implements Serializable {
    @Id
    private String id;

    private String userId;

    private String action;

    private final Date timestamp = new Date();

    private String ipAddress;

    private String oldValue;

    private String newValue;

}
