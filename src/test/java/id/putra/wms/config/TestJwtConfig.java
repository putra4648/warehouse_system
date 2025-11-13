package id.putra.wms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

@Configuration
public class TestJwtConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        return token -> {
            throw new JwtException("Test JwtDecoder stub - not used in tests");
        };
    }

}
