package id.putra.wms.module.inbound.service.core;

import java.util.List;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.shared.base.dto.response.ResponseData;

public interface InspectionService {
    ResponseData<QualityInspectionDto> create(QualityInspectionDto dto);
    ResponseData<QualityInspectionDto> update(QualityInspectionDto dto);
    ResponseData<Boolean> delete(Long id);
    ResponseData<QualityInspectionDto> getById(Long id);
    ResponseData<List<QualityInspectionDto>> getAll(QualityInspectionDto filter);
}
