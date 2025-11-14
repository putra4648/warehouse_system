package id.putra.wms.module.inbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inbound.dto.QualityInspectionDto;
import id.putra.wms.module.inbound.model.entity.QualityInspection;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QualityInspectionMapper {

    QualityInspection toEntity(QualityInspectionDto dto);

    QualityInspectionDto toDto(QualityInspection entity);

    List<QualityInspectionDto> toDtos(List<QualityInspection> entities);

}
