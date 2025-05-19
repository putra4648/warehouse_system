package id.putra.wms.service;

import id.putra.wms.dto.UserDto;
import id.putra.wms.dto.param.AdminParam;
import id.putra.wms.dto.param.PageableAndSortParam;
import id.putra.wms.dto.response.AppResponse;
import id.putra.wms.entity.Role;
import id.putra.wms.entity.User;
import id.putra.wms.exception.AdminException;
import id.putra.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

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


    public AppResponse.WithPaging<UserDto> getUsers(PageableAndSortParam pageableAndSortParam) throws AdminException {
        var page = pageableAndSortParam.getPage() == 0 ? 0 : pageableAndSortParam.getPage() - 1;
        var pageRequest = PageRequest.of(page, pageableAndSortParam.getSize());
        Page<User> users = userRepository.findAll(pageRequest);

        System.out.println(users.stream().count());
        var result = users.stream().map(user -> UserDto.builder().id(user.getId()).username(user.getUsername()).roles(user.getRoles().stream().map(Role::getRole).toList()).permissions(user.getCachedPermissions() != null ? user.getCachedPermissions().stream().toList() : Collections.emptyList()).build()).toList();

        return new AppResponse.WithPaging<>(pageRequest.getPageNumber(), pageRequest.getPageSize(), users.getTotalElements(), "Success", result);
    }
}
