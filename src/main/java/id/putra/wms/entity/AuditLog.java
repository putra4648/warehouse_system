package id.putra.wms.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

import java.io.Serializable;
import java.util.Date;

@Document
@Collection("user-audit-collection")
@Scope("user")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.USE_ATTRIBUTES, delimiter = "-")
    private String id;

    @Field("user_id")
    @IdPrefix
    private String userId;

    @Field
    private String action;

    @Field
    private final Date timestamp = new Date();

    @Field
    private String ipAddress;

    @Field("old_value")
    private String oldValue;

    @Field("new_value")
    private String newValue;

}
