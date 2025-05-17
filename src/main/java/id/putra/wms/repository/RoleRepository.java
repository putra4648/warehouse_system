package id.putra.wms.repository;

import id.putra.wms.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    List<Role> findByRole(String role);
}
