package id.putra.wms.module.inventory.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.inventory.dto.CategoryDto;

public interface CategoryQueryAdapter {

    Page<CategoryDto> getCategories(CategoryDto dto, Pageable pageable);

    CategoryDto getCategoryById(CategoryDto dto);

}
