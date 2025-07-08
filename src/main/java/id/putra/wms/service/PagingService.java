package id.putra.wms.service;

import org.springframework.data.domain.Page;

import id.putra.wms.dto.param.SearchParam;

public interface PagingService<T> {
    public Page<T> getAll(SearchParam param);
}
