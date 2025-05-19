package id.putra.wms.dto.param;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageableAndSortParam {
    @NotNull
    private int page;

    @NotNull
    private int size;
    
    private String sort;
}
