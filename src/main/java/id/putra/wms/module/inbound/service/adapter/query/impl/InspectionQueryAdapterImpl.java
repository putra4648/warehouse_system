package id.putra.wms.module.inbound.service.adapter.query.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.module.inbound.mapper.QualityInspectionMapper;
import id.putra.wms.module.inbound.model.repository.QualityInspectionRepository;
import id.putra.wms.module.inbound.service.adapter.query.InspectionQueryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InspectionQueryAdapterImpl implements InspectionQueryAdapter {

    private final QualityInspectionRepository qualityInspectionRepository;
    private final QualityInspectionMapper qualityInspectionMapper;

    @Override
    public QualityInspectionDto getById(@Nullable Long id) {
        if (id == null) return null;
        return qualityInspectionRepository.findById(id).map(qualityInspectionMapper::toDto).orElse(null);
    }

    @Override
    public List<QualityInspectionDto> getAll(QualityInspectionDto filter) {
        return qualityInspectionRepository.findAll().stream().map(qualityInspectionMapper::toDto).toList();
    }
}
