package id.putra.wms.service;

import id.putra.wms.dto.request.LoginBody;
import id.putra.wms.dto.request.RegisterBody;
import id.putra.wms.entity.Role;
import id.putra.wms.entity.User;
import id.putra.wms.exception.TokenException;
import id.putra.wms.exception.UserException;
import id.putra.wms.repository.RoleRepository;
import id.putra.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public void register(RegisterBody form) throws UserException {
        Optional<User> user = userRepository.findByUsername(form.getUsername());
        if (user.isPresent()) {
            throw new UserException("User already exist");
        }
        List<Role> roles = roleRepository.findByRole("ADMIN");
        userRepository.save(User
                .builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .roles(roles.stream().map(Role::getRole).collect(Collectors.toList()))
                .build()
        );
    }

    public String login(LoginBody body) throws TokenException {
        return jwtService.generateToken(body.username());
    }
}
