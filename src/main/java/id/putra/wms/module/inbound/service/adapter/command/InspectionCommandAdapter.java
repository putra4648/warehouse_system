package id.putra.wms.module.inbound.service.adapter.command;

import java.util.List;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;

public interface InspectionCommandAdapter {
    void add(List<QualityInspectionDto> dtos);

    void update(List<QualityInspectionDto> dtos);

    void delete(List<QualityInspectionDto> dtos);
}
