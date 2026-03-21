package id.putra.wms.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class DatabaseAudit {
    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public @NonNull Optional<String> getCurrentAuditor() {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                return java.util.Objects.requireNonNull(Optional.ofNullable(username));
            }
        };
    }
}
