package id.putra.wms.module.inventory.service.adapter.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.shared.base.dto.ProductDto;

public interface ProductQueryAdapter {

    Page<ProductDto> getProducts(ProductDto dto, Pageable pageable);

    ProductDto getProductById(ProductDto dto);

}
