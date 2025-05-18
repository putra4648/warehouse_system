package id.putra.wms.repository;

import id.putra.wms.entity.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.Optional;

public interface UserRepository extends CouchbaseRepository<User, String> {
    Optional<User> findByUsername(String username);
}
