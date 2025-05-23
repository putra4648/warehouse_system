package id.putra.wms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults()) // Enables OAuth2 login
                // Configures logout settings
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Redirects to the root URL on successful logout
                        .invalidateHttpSession(true) // Invalidates session to clear session data
                        .clearAuthentication(true) // Clears authentication details
                        .deleteCookies("JSESSIONID") // Deletes the session cookie
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/error/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .build();
    }

}
