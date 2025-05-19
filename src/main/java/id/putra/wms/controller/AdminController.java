package id.putra.wms.controller;

import id.putra.wms.dto.UserDto;
import id.putra.wms.dto.param.AdminParam;
import id.putra.wms.dto.param.PageableAndSortParam;
import id.putra.wms.dto.response.AppResponse;
import id.putra.wms.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("manage")
    public ResponseEntity<AppResponse.WithMessageOnly> roles(@Valid @RequestParam AdminParam param, @Valid @RequestBody UserDto body) {
        adminService.manageUser(param, body);
        return ResponseEntity.ok().body(new AppResponse.WithMessageOnly("Success"));
    }

    @GetMapping("users")
    public ResponseEntity<AppResponse.WithPaging<UserDto>> users(@Valid PageableAndSortParam pageableAndSortParam) {
        return ResponseEntity.ok().body(adminService.getUsers(pageableAndSortParam));
    }


}
