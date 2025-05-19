package id.putra.wms.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterBody(@NotBlank String username, @NotBlank String password) {
}
