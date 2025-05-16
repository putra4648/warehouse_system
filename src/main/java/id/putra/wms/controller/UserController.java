package id.putra.wms.controller;

import id.putra.wms.dto.request.RegisterForm;
import id.putra.wms.entity.User;
import id.putra.wms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("roles", authentication.getAuthorities());
        return "index";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("register")
    public String post(@ModelAttribute("registerForm") RegisterForm form, Model model) throws Exception {
        model.addAttribute("registerForm", form);
        authService.register(form);
        return "redirect:/login";
    }
}
