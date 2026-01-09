package id.putra.wms.module.inventory.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import id.putra.wms.shared.base.dto.ProductDto;

public interface ProductCoreService {

    Page<ProductDto> getProducts(ProductDto dto, Pageable pageable);

    ProductDto getProduct(ProductDto dto);

    void save(List<ProductDto> dtos);

    void update(List<ProductDto> dtos);

    void delete(List<ProductDto> dtos);

}
