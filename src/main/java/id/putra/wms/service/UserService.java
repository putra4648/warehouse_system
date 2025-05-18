package id.putra.wms.service;

import id.putra.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    //    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    ///  TODO: fix role mapping
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException(username);
//        }
//        var rolesIterator = roleRepository.findAllById(user.get().getRoles()).iterator();
//        var roles = new ArrayList<String>();
//        rolesIterator.forEachRemaining(role -> roles.add(role.getRole()));
//        if (roles.isEmpty()) {
//            throw new UsernameNotFoundException("Roles not found");
//        }
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.get().getUsername())
//                .password(user.get().getPassword())
//                .roles(roles.toArray(String[]::new))
//                .build();

        return null;
    }
}
