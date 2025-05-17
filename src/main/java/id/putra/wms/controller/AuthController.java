package id.putra.wms.controller;

import id.putra.wms.dto.request.LoginBody;
import id.putra.wms.dto.request.RegisterBody;
import id.putra.wms.exception.TokenException;
import id.putra.wms.exception.UserException;
import id.putra.wms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterBody form) throws UserException {
        authService.register(form);
        return ResponseEntity.ok("Register successful");
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginBody body) throws TokenException {
        return ResponseEntity.ok(authService.login(body));
    }


}
