package id.putra.wms.module.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.InjectionStrategy;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.model.entity.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {
    CategoryDto toDto(Category entity);

    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryDto dto);
}
