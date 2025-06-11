package id.putra.wms.service;

import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;

public interface PagingService<T> {
    public PagingResponse<T> getAll(SearchParam param);
}
