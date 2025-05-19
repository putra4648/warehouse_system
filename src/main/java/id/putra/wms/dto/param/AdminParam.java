package id.putra.wms.dto.param;

import id.putra.wms.enums.ParamActionEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AdminParam {
    @NotBlank
    private ParamActionEnum action;

    @NotBlank
    private String resource;
}
