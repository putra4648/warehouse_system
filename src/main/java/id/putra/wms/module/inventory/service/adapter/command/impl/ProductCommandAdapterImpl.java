package id.putra.wms.module.inventory.service.adapter.command.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.module.inventory.model.repository.ProductRepository;
import id.putra.wms.module.inventory.service.adapter.command.ProductCommandAdapter;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ProductCommandAdapterImpl implements ProductCommandAdapter {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void save(List<ProductDto> dtos) {
        var entities = dtos.stream().map(d -> productMapper.toEntity(d)).toList();
        productRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void update(List<ProductDto> dtos) {
        var entities = dtos.stream().map(d -> productMapper.toEntity(d)).toList();
        productRepository.saveAll(java.util.Objects.requireNonNull(entities));
    }

    @Override
    public void delete(List<ProductDto> dtos) {
        var ids = dtos.stream().map(d -> d.getId()).toList();
        productRepository.deleteAllById(java.util.Objects.requireNonNull(ids));
    }

}
