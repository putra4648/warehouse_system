package id.putra.wms.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    /// User ID
    private String id;

    @NotBlank
    private String username;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    // /// User permission e.g. READ, CREATE, UPDATE, DELETE
    // @NotNull
    // private List<String> permissions;

    /// User Role ID
    @NotNull
    private List<String> roles;

    /// User AuditLog information
    private AuditLogDto auditLog;
}
