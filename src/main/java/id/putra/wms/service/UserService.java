package id.putra.wms.service;

import id.putra.wms.entity.User;
import id.putra.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .authorities(user.get().getRoles().stream().map(SimpleGrantedAuthority::new).toList())
                .build();
    }
}
