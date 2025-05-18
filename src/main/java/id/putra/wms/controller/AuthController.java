package id.putra.wms.controller;

import id.putra.wms.dto.request.RegisterBody;
import id.putra.wms.dto.response.AppSuccessResponse;
import id.putra.wms.exception.UserException;
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
    public ResponseEntity<AppSuccessResponse> register(@Valid @RequestBody RegisterBody form) throws UserException {
        authService.register(form);
        return ResponseEntity.ok(new AppSuccessResponse("Success register"));
    }

/// TODO: change login
//    @PostMapping("login")
//    public ResponseEntity<AppSuccessResponse> login(@Valid @RequestBody LoginBody body) throws TokenException {
//        return ResponseEntity.ok(new AppSuccessResponse(authService.login(body)));
//    }


}
