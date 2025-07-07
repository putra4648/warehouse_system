package id.putra.wms.dto.param;

import lombok.Data;

@Data
public class SortParam {
    private String column;
    private String dir;
    private String field;
}
