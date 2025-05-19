package id.putra.wms.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginBody(@NotBlank String username, @NotBlank String password) {
}
