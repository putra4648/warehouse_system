package id.putra.wms.module.outbound.service.adapter.query;

import java.util.List;
import id.putra.wms.module.outbound.dto.PackingDto;

public interface PackingQueryAdapter {
    PackingDto getById(Long id);
    List<PackingDto> getAll(PackingDto filter);
}
