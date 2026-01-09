package id.putra.wms.module.inventory.service.core.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.service.adapter.command.CategoryCommandAdapter;
import id.putra.wms.module.inventory.service.adapter.query.CategoryQueryAdapter;
import id.putra.wms.module.inventory.service.core.CategoryCoreService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryCoreService {
    private final CategoryQueryAdapter categoryQueryAdapter;
    private final CategoryCommandAdapter categoryCommandAdapter;

    @Override
    public Page<CategoryDto> getCategories(CategoryDto dto, Pageable pageable) {
        return categoryQueryAdapter.getCategories(dto, pageable);
    }

    @Override
    public CategoryDto getCategory(CategoryDto dto) {
        return categoryQueryAdapter.getCategoryById(dto);
    }

    @Override
    public void save(List<CategoryDto> dtos) {
        categoryCommandAdapter.save(dtos);
    }

    @Override
    public void update(List<CategoryDto> dtos) {
        categoryCommandAdapter.update(dtos);
    }

    @Override
    public void delete(List<CategoryDto> dtos) {
        categoryCommandAdapter.delete(dtos);
    }

}
