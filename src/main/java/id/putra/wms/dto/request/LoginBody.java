package id.putra.wms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record LoginBody(@NotBlank String username, @NotBlank String password) {
}
