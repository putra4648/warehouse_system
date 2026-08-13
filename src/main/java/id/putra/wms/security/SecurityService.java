package id.putra.wms.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityService {

    private final AuthenticationContext authenticationContext;

    public SecurityService(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public Optional<AuthenticatedUser> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() 
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        String name = "User";
        String email = "";
        String picture = "";

        if (principal instanceof OidcUser oidcUser) {
            name = oidcUser.getFullName() != null ? oidcUser.getFullName() : oidcUser.getGivenName();
            email = oidcUser.getEmail() != null ? oidcUser.getEmail() : "";
            picture = oidcUser.getPicture() != null ? oidcUser.getPicture() : "";
        } else if (principal instanceof OAuth2User oauth2User) {
            name = oauth2User.getAttribute("name");
            email = oauth2User.getAttribute("email");
            picture = oauth2User.getAttribute("picture");
        } else if (principal != null) {
            name = principal.toString();
        }

        if (name == null || name.isBlank()) {
            name = (email != null && !email.isBlank()) ? email : "User";
        }

        return Optional.of(new AuthenticatedUser(name, email != null ? email : "", picture != null ? picture : ""));
    }

    public void logout() {
        authenticationContext.logout();
    }

    public record AuthenticatedUser(String name, String email, String pictureUrl) {}
}
