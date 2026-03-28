package id.putra.wms.module.inbound.service.core.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.module.inbound.service.adapter.command.InspectionCommandAdapter;
import id.putra.wms.module.inbound.service.adapter.query.InspectionQueryAdapter;
import id.putra.wms.module.inbound.service.core.InspectionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InspectionServiceImpl implements InspectionService {

    private final InspectionCommandAdapter inspectionCommandAdapter;
    private final InspectionQueryAdapter inspectionQueryAdapter;

    @Override
    public QualityInspectionDto create(QualityInspectionDto dto) {
        return inspectionCommandAdapter.add(dto);
    }

    @Override
    public QualityInspectionDto update(QualityInspectionDto dto) {
        return inspectionCommandAdapter.update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        return inspectionCommandAdapter.delete(id);
    }

    @Override
    public QualityInspectionDto getById(Long id) {
        return inspectionQueryAdapter.getById(id);
    }

    @Override
    public List<QualityInspectionDto> getAll(QualityInspectionDto filter) {
        return inspectionQueryAdapter.getAll(filter);
    }
}
