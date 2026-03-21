package id.putra.wms.module.inbound.service.adapter.command;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;

public interface InspectionCommandAdapter {
    QualityInspectionDto add(QualityInspectionDto dto);

    QualityInspectionDto update(QualityInspectionDto dto);

    Boolean delete(QualityInspectionDto dto);
}
