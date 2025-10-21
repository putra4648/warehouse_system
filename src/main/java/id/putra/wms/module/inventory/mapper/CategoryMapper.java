package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.model.entity.Category;
import id.putra.wms.shared.mapper.ProductMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ProductMapper.class })
public interface CategoryMapper {
    CategoryDto toDto(Category entity);

    Category toEntity(CategoryDto dto);
}
