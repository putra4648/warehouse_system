package id.putra.wms.dto.param;

import id.putra.wms.enums.ParamActionEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminParam {
    @NotNull
    private ParamActionEnum action;

    @NotBlank
    private String resource;
}
