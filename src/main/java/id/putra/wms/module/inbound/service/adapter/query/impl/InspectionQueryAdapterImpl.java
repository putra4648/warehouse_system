package id.putra.wms.module.inbound.service.adapter.query.impl;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.converter.QualityInspectionConverter;
import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.module.inbound.model.repository.QualityInspectionRepository;
import id.putra.wms.module.inbound.service.adapter.query.InspectionQueryAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InspectionQueryAdapterImpl implements InspectionQueryAdapter {

    private final QualityInspectionRepository qualityInspectionRepository;

    @Override
    public QualityInspectionDto getById(@NonNull Long id) {
        return qualityInspectionRepository.findById(id).map(QualityInspectionConverter::toDto).orElse(null);
    }

    @Override
    public List<QualityInspectionDto> getAll(QualityInspectionDto filter) {
        return qualityInspectionRepository.findAll().stream().map(QualityInspectionConverter::toDto).toList();
    }
}
