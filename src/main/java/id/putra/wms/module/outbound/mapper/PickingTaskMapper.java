package id.putra.wms.module.outbound.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.module.outbound.dto.PickingTaskDto;
import id.putra.wms.module.outbound.model.entity.PickingTask;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { InventoryItemMapper.class,
        SalesOrderLineMapper.class })
public interface PickingTaskMapper {

    PickingTask toEntity(PickingTaskDto dto);

    PickingTaskDto toDto(PickingTask entity);

    List<PickingTaskDto> toDtos(List<PickingTask> entities);

}
