package id.putra.wms.module.inventory.service.core.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.module.inventory.service.adapter.command.ProductCommandAdapter;
import id.putra.wms.module.inventory.service.adapter.query.ProductQueryAdapter;
import id.putra.wms.module.inventory.service.core.ProductCoreService;
import id.putra.wms.shared.base.dto.ProductDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductCoreService {
    private final ProductQueryAdapter productQueryAdapter;
    private final ProductCommandAdapter productCommandAdapter;

    @Override
    public Page<ProductDto> getProducts(ProductDto dto, Pageable pageable) {
        return productQueryAdapter.getProducts(dto, pageable);
    }

    @Override
    public ProductDto getProduct(ProductDto dto) {
        return productQueryAdapter.getProductById(dto);
    }

    @Override
    public ProductDto save(ProductDto dto) {
        return productCommandAdapter.save(dto);
    }

    @Override
    public ProductDto update(ProductDto dto) {
        return productCommandAdapter.update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        return productCommandAdapter.delete(id);
    }

}
