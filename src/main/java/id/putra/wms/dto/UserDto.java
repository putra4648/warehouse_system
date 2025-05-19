package id.putra.wms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    ///  User ID
    private String id;

    @NotBlank
    private String username;

    ///  User permission e.g. READ, CREATE, UPDATE, DELETE
    @NotNull
    private List<String> permissions;

    ///  User Role ID
    @NotNull
    private List<String> roles;

    ///  User AuditLog information
    private AuditLogDto auditLog;
}
