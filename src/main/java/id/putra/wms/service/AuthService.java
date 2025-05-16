package id.putra.wms.service;

import id.putra.wms.dto.request.RegisterForm;
import id.putra.wms.entity.User;
import id.putra.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void register(RegisterForm form) throws Exception {
        Optional<User> user = userRepository.findByUsername(form.getUsername());
        if (user.isPresent()) {
            throw new Exception("User already exist");
        }
        userRepository.save(User.builder().username(form.getUsername()).password(passwordEncoder.encode(form.getPassword())).build());
    }
}
