package id.putra.wms.module.inbound.service.adapter.query;

import java.util.List;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;

public interface InspectionQueryAdapter {
    QualityInspectionDto getById(Long id);

    List<QualityInspectionDto> getAll(QualityInspectionDto filter);
}
