package id.putra.wms.service;

import id.putra.wms.dto.request.LoginBody;
import id.putra.wms.dto.request.RegisterBody;
import id.putra.wms.entity.Role;
import id.putra.wms.entity.User;
import id.putra.wms.exception.AuthException;
import id.putra.wms.repository.RoleRepository;
import id.putra.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public void register(RegisterBody form) {
        Optional<User> user = userRepository.findByUsername(form.username());
        if (user.isPresent()) {
            throw new AuthException("User already exist");
        } else {
            Set<Role> roles = roleRepository.findByRoleIn(user.get().getRoles().stream().map(Role::getRole).collect(Collectors.toSet()));
            userRepository.save(User.builder().username(form.username()).password(passwordEncoder.encode(form.password())).roleIds(roles.stream().map(Role::getId).collect(Collectors.toSet())).build());
        }
    }

    public String login(LoginBody body) {
        return jwtService.generateToken(body.username());
    }
}
