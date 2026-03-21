package id.putra.wms.module.inventory.service.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.shared.base.dto.ProductDto;

public interface ProductCoreService {

    Page<ProductDto> getProducts(ProductDto dto, Pageable pageable);

    ProductDto getProduct(ProductDto dto);

    ProductDto save(ProductDto dto);

    ProductDto update(ProductDto dto);

    Boolean delete(Long id);

}
