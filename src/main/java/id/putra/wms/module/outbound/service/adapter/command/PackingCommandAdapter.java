package id.putra.wms.module.outbound.service.adapter.command;

import java.util.List;
import id.putra.wms.module.outbound.dto.PackingDto;

public interface PackingCommandAdapter {
    void add(List<PackingDto> dtos);
    void update(List<PackingDto> dtos);
    void delete(List<PackingDto> dtos);
}
