package id.putra.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

import java.io.Serializable;
import java.util.Set;

@Document
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Scope("user")
@Collection("role-collection")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private String role;

    @Field
    private String description;

    @Field("permission_ids")
    private Set<Permission> permissionIds;


}
