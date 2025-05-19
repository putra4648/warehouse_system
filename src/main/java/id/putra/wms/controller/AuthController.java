package id.putra.wms.controller;

import id.putra.wms.dto.request.LoginBody;
import id.putra.wms.dto.request.RegisterBody;
import id.putra.wms.dto.response.AppResponse;
import id.putra.wms.exception.AuthException;
import id.putra.wms.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AppResponse.WithMessageOnly> register(@Valid @RequestBody RegisterBody form) throws AuthException {
        authService.register(form);
        return ResponseEntity.ok(new AppResponse.WithMessageOnly("Success register"));
    }

    @PostMapping("login")
    public ResponseEntity<AppResponse.WithMessageOnly> login(@Valid @RequestBody LoginBody body) {
        return ResponseEntity.ok(new AppResponse.WithMessageOnly(authService.login(body)));
    }


}
