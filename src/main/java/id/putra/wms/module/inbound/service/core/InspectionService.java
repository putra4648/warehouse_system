package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;

public interface InspectionService {
    QualityInspectionDto create(QualityInspectionDto dto);

    QualityInspectionDto update(QualityInspectionDto dto);

    Boolean delete(Long id);

    QualityInspectionDto getById(Long id);

    List<QualityInspectionDto> getAll(QualityInspectionDto filter);
}
