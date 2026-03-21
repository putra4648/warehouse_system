package id.putra.wms.module.outbound.service.adapter.command;

import java.util.List;
import id.putra.wms.module.outbound.dto.CarrierDto;

public interface CarrierCommandAdapter {
    void add(List<CarrierDto> dtos);
    void update(List<CarrierDto> dtos);
    void delete(List<CarrierDto> dtos);
}
