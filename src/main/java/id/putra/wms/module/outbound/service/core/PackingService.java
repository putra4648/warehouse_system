package id.putra.wms.module.outbound.service.core;

import java.util.List;
import id.putra.wms.module.outbound.dto.PackingDto;

public interface PackingService {
    PackingDto create(PackingDto dto);
    PackingDto update(PackingDto dto);
    Boolean delete(Long id);
    PackingDto getById(Long id);
    List<PackingDto> getAll(PackingDto filter);
}
