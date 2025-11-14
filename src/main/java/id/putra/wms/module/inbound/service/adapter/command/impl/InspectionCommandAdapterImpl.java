package id.putra.wms.module.inbound.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.module.inbound.mapper.QualityInspectionMapper;
import id.putra.wms.module.inbound.model.repository.QualityInspectionRepository;
import id.putra.wms.module.inbound.service.adapter.command.InspectionCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class InspectionCommandAdapterImpl implements InspectionCommandAdapter {

    private final QualityInspectionRepository qualityInspectionRepository;
    private final QualityInspectionMapper qualityInspectionMapper;

    @Override
    public void add(List<QualityInspectionDto> dtos) {
        var entities = dtos.stream().map(qualityInspectionMapper::toEntity).toList();
        qualityInspectionRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<QualityInspectionDto> dtos) {
        var entities = dtos.stream().map(qualityInspectionMapper::toEntity).toList();
        qualityInspectionRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<QualityInspectionDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        qualityInspectionRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }
}
