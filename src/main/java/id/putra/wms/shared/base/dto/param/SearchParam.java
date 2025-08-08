package id.putra.wms.shared.base.dto.param;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchParam extends ActionsParam {

    private Integer page;

    private Integer size;

    private List<FilterParam> filter = new ArrayList<>();

    private List<SortParam> sort = new ArrayList<>();

}
