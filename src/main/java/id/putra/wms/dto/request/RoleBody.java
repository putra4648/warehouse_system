package id.putra.wms.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RoleBody(@NotBlank String role) {
}
