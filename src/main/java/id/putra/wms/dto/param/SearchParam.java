package id.putra.wms.dto.param;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SearchParam {
    private Integer page;
    private Integer size;
    private List<FilterParam> filter = new ArrayList<>();
    private List<SortParam> sort = new ArrayList<>();
}
