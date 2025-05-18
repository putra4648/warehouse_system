package id.putra.wms.service;

import id.putra.wms.dto.request.LoginBody;
import id.putra.wms.dto.request.RegisterBody;
import id.putra.wms.dto.request.RoleBody;
import id.putra.wms.entity.Role;
import id.putra.wms.entity.User;
import id.putra.wms.exception.RolesException;
import id.putra.wms.exception.TokenException;
import id.putra.wms.exception.UserException;
import id.putra.wms.repository.RoleRepository;
import id.putra.wms.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public void register(RegisterBody form) throws UserException {
        Optional<User> user = userRepository.findByUsername(form.username());
        if (user.isPresent()) {
            throw new UserException("User already exist");
        }
        List<Role> roles = roleRepository.findByRole("USER");
        if (roles.isEmpty()) {
            throw new RolesException("No roles found");
        }
        userRepository.save(User
                .builder()
                .username(form.username())
                .password(passwordEncoder.encode(form.password()))
                .roles(roles.stream().map(Role::getId).toList())
                .build()
        );
    }

    public String login(LoginBody body) throws TokenException {
        return jwtService.generateToken(body.username());
    }

    public void roles(RoleBody body) throws RolesException {
        List<Role> roles = roleRepository.findByRole(body.role());
        if (roles.isEmpty()) {
            roleRepository.save(Role.builder().role(body.role()).build());
        } else {
            throw new RolesException("Roles already exist");
        }
    }
}
