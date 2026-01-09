package id.putra.wms.module.inventory.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.mapper.CategoryMapper;
import id.putra.wms.module.inventory.model.repository.CategoryRepository;
import id.putra.wms.module.inventory.service.adapter.command.CategoryCommandAdapter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class CategoryCommandAdapterImpl implements CategoryCommandAdapter {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void save(List<CategoryDto> dtos) {
        var entities = dtos.stream().map(d -> categoryMapper.toEntity(d)).toList();
        categoryRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<CategoryDto> dtos) {
        var entities = dtos.stream().map(d -> categoryMapper.toEntity(d)).toList();
        categoryRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<CategoryDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        categoryRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }

}
