package id.putra.wms.controller;

import id.putra.wms.dto.request.LoginBody;
import id.putra.wms.dto.request.RegisterBody;
import id.putra.wms.dto.request.RoleBody;
import id.putra.wms.dto.response.AppSuccessResponse;
import id.putra.wms.exception.RolesException;
import id.putra.wms.exception.TokenException;
import id.putra.wms.exception.UserException;
import id.putra.wms.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AppSuccessResponse> register(@Valid @RequestBody RegisterBody form) throws UserException {
        authService.register(form);
        return ResponseEntity.ok(new AppSuccessResponse("Success register"));
    }

    @PostMapping("login")
    public ResponseEntity<AppSuccessResponse> login(@Valid @RequestBody LoginBody body) throws TokenException {
        return ResponseEntity.ok(new AppSuccessResponse(authService.login(body)));
    }

    @PostMapping("roles")
    public ResponseEntity<AppSuccessResponse> roles(@Valid @RequestBody RoleBody body) throws RolesException {
        authService.roles(body);
        return ResponseEntity.ok().body(new AppSuccessResponse("Success add new role"));
    }


}
