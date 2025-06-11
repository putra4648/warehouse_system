package id.putra.wms.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import id.putra.wms.dto.ProductDto;
import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;
import id.putra.wms.entity.Product;
import id.putra.wms.exceptions.ProductException;
import id.putra.wms.repository.ProductRepository;
import id.putra.wms.service.CRUDService;
import id.putra.wms.service.PagingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements CRUDService<ProductDto, ProductException>, PagingService<ProductDto> {

    private final ProductRepository productRepository;

    @Override
    @Transactional(rollbackOn = { Exception.class, ProductException.class })
    public void add(ProductDto dto) {
        if (productRepository.existsById(dto.getSku())) {
            throw new ProductException("Product %s already exist".formatted(dto.getSku()));
        }

        var entity = new Product();
        entity.setId(dto.getSku());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setMinStock(dto.getMin_stock());
        entity.setMaxStock(dto.getMax_stock());
        entity.setIsActive(dto.getIs_active());

        productRepository.save(entity);
    }

    @Override
    public void update(ProductDto dto) throws ProductException {

        if (productRepository.existsById(dto.getSku())) {
            var entity = productRepository.findById(dto.getSku()).get();

            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setMinStock(dto.getMin_stock());
            entity.setMaxStock(dto.getMax_stock());
            entity.setIsActive(dto.getIs_active());

            productRepository.save(entity);
        } else {
            throw new ProductException("Product %s not exist".formatted(dto.getSku()));
        }

    }

    @Override
    public void delete(ProductDto form) throws ProductException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public PagingResponse<ProductDto> getAll(SearchParam param) {
        var newPageable = PageRequest.of(param.getPage() - 1, param.getSize(),
                param.getSort() != null ? Sort.by(param.getSort().stream().map(s -> {
                    String field = "";
                    switch (s.getField()) {
                        case "received_date":
                            field = "receivedDate";
                            break;
                        default:
                            field = s.getField();
                            break;
                    }

                    if (s.getDir().equals("asc")) {

                        return Order.asc(field);
                    } else {
                        return Order.desc(field);
                    }
                }).toList()) : Sort.unsorted());
        var entity = new Product();
        // entity.setSupplier(param.getFilter().isEmpty() ? "" : (String)
        // param.getFilter().get(0).getValue());
        ExampleMatcher example = ExampleMatcher.matching().withMatcher("supplier",
                (matcher) -> matcher.ignoreCase().startsWith());
        var page = productRepository.findAll(Example.of(entity, example), newPageable);
        var result = page.getContent().stream().map(data -> mapToDto(data)).toList();
        return new PagingResponse<>((long) page.getTotalPages(), result);
    }

    @Override
    public ProductDto getProductById(String id) {
        var dto = new ProductDto();

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return mapToDto(product.get());
        }

        return dto;
    }

    private ProductDto mapToDto(Product product) {
        var dto = new ProductDto();

        dto.setSku(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setMin_stock(product.getMinStock());
        dto.setMax_stock(product.getMaxStock());
        dto.setCategory_id(product.getCategory() != null ? product.getCategory().getId().toString() : null);
        dto.setStatus(product.getIsActive().toString());

        return dto;
    }

}
