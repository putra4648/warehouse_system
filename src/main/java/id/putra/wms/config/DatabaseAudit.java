package id.putra.wms.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@Configuration
@EnableJpaAuditing
public class DatabaseAudit {
    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public @NonNull Optional<String> getCurrentAuditor() {
                var authentication = SecurityContextHolder.getContext().getAuthentication();
                return Optional.ofNullable(authentication).filter(auth -> auth.isAuthenticated())
                        .map(OAuth2AuthenticationToken.class::cast)
                        .map(user -> user.getPrincipal().getAttribute("preferred_username"));
            }
        };
    }
}
