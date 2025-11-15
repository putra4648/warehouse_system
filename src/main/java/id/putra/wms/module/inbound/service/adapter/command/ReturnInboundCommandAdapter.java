package id.putra.wms.module.inbound.service.adapter.command;

import java.util.List;

public interface ReturnInboundCommandAdapter {
    void add(List<Object> dtos);

    void update(List<Object> dtos);

    void delete(List<Object> dtos);
}
