package id.putra.wms.module.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User", description = "User profile and authentication APIs")
@SecurityRequirement(name = "oauth2")
public class UserController {
    @GetMapping("/profile")
    @Operation(summary = "Get user profile", description = "Retrieve the authenticated user's profile information")
    public ResponseEntity<Map<String, Object>> getProfile(
            @Parameter(description = "JWT token from authenticated user", hidden = true) @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(Map.of(
                "message", "This is a public profile endpoint.",
                "user", jwt.getClaims()));
    }

}
