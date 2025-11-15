package id.putra.wms.module.inbound.service.adapter.command;

import java.util.List;

public interface PutawayCommandAdapter {
    void add(List<Object> dtos);

    void update(List<Object> dtos);

    void delete(List<Object> dtos);
}
