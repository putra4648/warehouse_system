package id.putra.wms.util;

import id.putra.wms.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JWTUtilTest {
    @Autowired
    JwtService jwtService;


    @Test
    void whenGenerateToken_shouldNotThrowException() throws Exception {
        String token = jwtService.generateToken("admin");
        assertNotNull(token);
        System.out.println(token);
    }

    @Test
    void whenTokenProvided_tokenShouldVerifiedTrue() throws Exception {
        String token = "eyJhbGciOiJFZERTQSJ9.eyJzdWIiOiJhZG1pbiJ9.tkd3OM8efNd24ClPUy82pdR-mrd3yUG6zmPHUbE3o33olxuD8qf142PDq9mOV7P9ON8IwNNq_9x3WiHQyXAGAQ";
        assertTrue(jwtService.verifyToken(token));
    }
}