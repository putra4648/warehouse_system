package id.putra.wms.module.inventory.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.inventory.dto.CategoryDto;

public interface CategoryCoreService {

    Page<CategoryDto> getCategories(CategoryDto dto, Pageable pageable);

    CategoryDto getCategory(CategoryDto dto);

    void save(List<CategoryDto> dtos);

    void update(List<CategoryDto> dtos);

    void delete(List<CategoryDto> dtos);

}
