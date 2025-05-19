package id.putra.wms.repository;

import id.putra.wms.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, String> {
    Set<Role> findByRoleIn(Collection<String> roles);

}
