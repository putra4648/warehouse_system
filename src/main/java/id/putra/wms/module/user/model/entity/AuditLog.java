package id.putra.wms.module.user.model.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import id.putra.wms.shared.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "audit_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
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
