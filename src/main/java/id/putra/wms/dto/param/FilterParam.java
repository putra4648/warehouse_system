package id.putra.wms.dto.param;

import lombok.Data;

@Data
public class FilterParam {
    private String field;
    private String type;
    private Object value;
}
