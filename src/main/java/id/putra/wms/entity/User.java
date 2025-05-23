package id.putra.wms.entity;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User implements UserDetails {
    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String firstname;

    private String lastname;

    private Set<String> roleIds;

    private Set<String> cachedPermissions;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modfiedDate;

    @Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
