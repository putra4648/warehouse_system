package id.putra.wms.module.inbound.service.adapter.command.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.module.inbound.mapper.QualityInspectionMapper;
import id.putra.wms.module.inbound.model.repository.QualityInspectionRepository;
import id.putra.wms.module.inbound.service.adapter.command.InspectionCommandAdapter;
import id.putra.wms.shared.enums.ResponseEnum;
import id.putra.wms.shared.exceptions.ModuleException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class InspectionCommandAdapterImpl implements InspectionCommandAdapter {

    private final QualityInspectionRepository qualityInspectionRepository;
    private final QualityInspectionMapper qualityInspectionMapper;

    @Override
    public QualityInspectionDto add(QualityInspectionDto dto) {
        var entity = qualityInspectionMapper.toEntity(dto);
        return qualityInspectionMapper.toDto(qualityInspectionRepository.save(Objects.requireNonNull(entity)));
    }

    @Override
    public QualityInspectionDto update(QualityInspectionDto dto) {
        var entity = qualityInspectionMapper.toEntity(dto);
        return qualityInspectionMapper.toDto(qualityInspectionRepository.save(Objects.requireNonNull(entity)));
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            throw new ModuleException(ResponseEnum.INVALID_PARAM);
        }
        if (qualityInspectionRepository.existsById(id)) {
            qualityInspectionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
