package id.putra.wms.service;

import id.putra.wms.dto.UserDto;
import id.putra.wms.dto.param.AdminParam;
import id.putra.wms.exception.AdminException;
import id.putra.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    /// TODO: re-configure manage user by manual mapping entity
    @Transactional
    public void manageUser(AdminParam param, UserDto userDto) throws AdminException {
        switch (param.getAction()) {
            case ADD -> {
                if (param.getResource().equalsIgnoreCase("user")) {

                }
            }
            case EDIT -> {

            }
            case DELETE -> {
            }
            default -> throw new IllegalStateException("Unexpected value: " + param.getAction());
        }
    }
}
