package id.putra.wms.module.inventory.service.adapter.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import id.putra.wms.module.inventory.model.repository.ProductRepository;
import id.putra.wms.module.inventory.service.adapter.query.ProductQueryAdapter;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.base.entity.Product;
import id.putra.wms.shared.mapper.ProductMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductQueryAdapterImpl implements ProductQueryAdapter {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto getProductById(ProductDto dto) {
        String id = java.util.Objects.requireNonNull(dto.getId());
        var product = productRepository.findById(id);
        return product.map(productMapper::toDto).orElse(null);
    }

    @Override
    public Page<ProductDto> getProducts(ProductDto dto, Pageable pageable) {
        Specification<Product> specs = (root, criteria, builder) -> {
            Predicate name = builder.like(root.get("name"),
                    "%" + (StringUtils.hasText(dto.getName()) ? dto.getName() : "") + "%");
            return builder.and(name);
        };
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        return productRepository.findAll(specs, safePageable).map(productMapper::toDto);
    }

}
