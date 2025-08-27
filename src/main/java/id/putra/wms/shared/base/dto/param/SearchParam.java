package id.putra.wms.shared.base.dto.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchParam {
    private FilterParam filter;
    private SortParam sort;
}
