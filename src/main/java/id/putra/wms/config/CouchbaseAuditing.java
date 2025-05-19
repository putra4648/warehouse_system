package id.putra.wms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Configuration
@EnableCouchbaseAuditing
public class CouchbaseAuditing {
    @Bean
    AuditorAware<User> auditorProvider() {
        return new AuditorAware<User>() {
            @Override
            public @NonNull Optional<User> getCurrentAuditor() {
                return Optional.ofNullable(SecurityContextHolder.getContext())
                        .map(SecurityContext::getAuthentication)
                        .filter(Authentication::isAuthenticated)
                        .map(Authentication::getPrincipal)
                        .map(principal -> {
                            if (principal instanceof User) return (User) principal;
                            return null;
                        });
            }
        };
    }
}
