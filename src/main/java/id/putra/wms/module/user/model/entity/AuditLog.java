package id.putra.wms.module.user.model.entity;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "audit_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class AuditLog extends BaseEntity implements Serializable {
    @Id
    @Column(name = "audit_id")
    private String id;
    
    @Column(name = "user_id")
    private String userId;

    private String entity;
    private String action;
    private ZonedDateTime timestamp;
    private String ipAddress;

    @Lob
    private String oldValue;
    @Lob
    private String newValue;
}
