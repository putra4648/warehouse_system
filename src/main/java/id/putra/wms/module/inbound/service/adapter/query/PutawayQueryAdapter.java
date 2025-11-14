package id.putra.wms.module.inbound.service.adapter.query;

import java.util.List;

public interface PutawayQueryAdapter {
    Object getById(Long id);

    List<Object> getAll(Object filter);
}
