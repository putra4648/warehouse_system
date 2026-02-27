package id.putra.wms.module.inventory.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.mapper.CategoryMapper;
import id.putra.wms.module.inventory.model.entity.Category;
import id.putra.wms.module.inventory.model.repository.CategoryRepository;
import id.putra.wms.module.inventory.service.adapter.query.CategoryQueryAdapter;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryQueryAdapterImpl implements CategoryQueryAdapter {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto getCategoryById(CategoryDto dto) {
        Long id = java.util.Objects.requireNonNull(dto.getId());
        var category = categoryRepository.findById(id);
        return category.map(categoryMapper::toDto).orElse(null);
    }

    @Override
    public Page<CategoryDto> getCategories(CategoryDto dto, Pageable pageable) {
        Specification<Category> specs = (root, criteria, builder) -> {
            Predicate name = builder.like(root.get("name"),
                    "%" + (StringUtils.hasText(dto.getName()) ? dto.getName() : "") + "%");
            return builder.and(name);
        };
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return categoryRepository.findAll(specs, safePageable).map(categoryMapper::toDto);
    }

}
