package id.putra.wms.service;

import org.springframework.data.domain.Page;

import id.putra.wms.shared.base.dto.param.SearchParam;

public interface PagingService<T> {
    public Page<T> getAll(SearchParam param);
}
