package id.putra.wms.module.inventory.service.adapter.command.impl;

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
    public ProductDto save(ProductDto dto) {
        var entity = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(entity));
    }

    @Override
    public ProductDto update(ProductDto dto) {
        var entity = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(entity));
    }

    @Override
    public Boolean delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
