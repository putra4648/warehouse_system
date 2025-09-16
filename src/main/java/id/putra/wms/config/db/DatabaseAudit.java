package id.putra.wms.config.db;

import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class DatabaseAudit {
    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public @NonNull Optional<String> getCurrentAuditor() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication == null || !authentication.isAuthenticated()) {
                    return Optional.empty();
                }

                // Handle OAuth2 Authentication
                if (authentication instanceof OAuth2AuthenticationToken) {
                    OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
                    OAuth2User oauth2User = oauth2Token.getPrincipal();

                    // Extract user identifier - adjust based on your OAuth2 provider
                    String userId = extractUserId(oauth2User);
                    return Optional.ofNullable(userId);
                }

                // Handle JWT tokens
                if (authentication instanceof JwtAuthenticationToken) {
                    JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
                    Jwt jwt = jwtToken.getToken();

                    // Extract from JWT claims
                    String userId = jwt.getClaimAsString("sub"); // or "email", "preferred_username", etc.
                    return Optional.ofNullable(userId);
                }

                // Fallback to username
                return Optional.ofNullable(authentication.getName());
            }
        };
    }

    private String extractUserId(OAuth2User oauth2User) {
        // Customize based on your OAuth2 provider
        Map<String, Object> attributes = oauth2User.getAttributes();

        // For Google
        if (attributes.containsKey("sub")) {
            return (String) attributes.get("sub");
        }

        // For GitHub
        if (attributes.containsKey("id")) {
            return String.valueOf(attributes.get("id"));
        }

        // For Microsoft/Azure
        if (attributes.containsKey("oid")) {
            return (String) attributes.get("oid");
        }

        // Default to email
        return (String) attributes.get("email");
    }
}
