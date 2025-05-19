package id.putra.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.query.N1qlJoin;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

import java.io.Serializable;
import java.util.Set;

@Document
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Scope("user")
@Collection("user-collection")
public class User extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private String username;

    @Field
    private String password;

    @Field
    private String email;

    @Field
    private String firstname;

    @Field
    private String lastname;

    @Field("role_ids")
    private Set<String> roleIds;

    @Field("cached_permissions")
    private Set<String> cachedPermissions;

    @N1qlJoin(on = "rks.id in lks.role_ids")
    private Set<Role> roles;
}
