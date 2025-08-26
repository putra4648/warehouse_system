package id.putra.wms.module.inventory.service.core;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import id.putra.wms.module.inventory.dto.form.ProductForm;
import id.putra.wms.module.inventory.model.entity.Product;
import id.putra.wms.module.inventory.model.repository.ProductRepository;
import id.putra.wms.service.CRUDService;
import id.putra.wms.service.PagingService;
import id.putra.wms.shared.base.dto.param.SearchParam;
import id.putra.wms.shared.exceptions.MasterDataException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements PagingService<ProductForm> {

    private final ProductRepository productRepository;

    @Transactional(rollbackOn = { Exception.class, MasterDataException.class })
    public void add(ProductForm dto) {
        if (productRepository.existsById(dto.getSku())) {
            throw new MasterDataException("Product %s already exist".formatted(dto.getSku()));
        }

        var entity = new Product();
        entity.setId(dto.getSku());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setMinStock(dto.getMinStock());
        entity.setMaxStock(dto.getMaxStock());
        entity.setIsActive(dto.getIsActive());

        productRepository.save(entity);
    }

    @Transactional(rollbackOn = { Exception.class, MasterDataException.class })
    public void update(ProductForm dto) throws MasterDataException {

        if (productRepository.existsById(dto.getSku())) {
            var entity = productRepository.findById(dto.getSku()).get();

            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setMinStock(dto.getMinStock());
            entity.setMaxStock(dto.getMaxStock());
            entity.setIsActive(dto.getIsActive());

            productRepository.save(entity);
        } else {
            throw new MasterDataException("Product %s not exist".formatted(dto.getSku()));
        }

    }

    @Transactional(rollbackOn = { Exception.class, MasterDataException.class })
    public void delete(String id) throws MasterDataException {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new MasterDataException("Product %s not exist".formatted(id));
        }
    }

    @Override
    public Page<ProductForm> getAll(SearchParam param) {
        // var newPageable = PageRequest.of(param.getPage() - 1, param.getSize(),
        //         param.getSort() != null ? Sort.by(param.getSort().stream().map(s -> {
        //             String field = "";
        //             switch (s.getField()) {
        //                 case "received_date":
        //                     field = "receivedDate";
        //                     break;
        //                 default:
        //                     field = s.getField();
        //                     break;
        //             }

        //             if (s.getDir().equals("asc")) {

        //                 return Order.asc(field);
        //             } else {
        //                 return Order.desc(field);
        //             }
        //         }).toList()) : Sort.unsorted());
        // var entity = new Product();
        // entity.setId(param.getFilter().isEmpty() ? "" : (String) param.getFilter().getFirst().getValue());
        // entity.setName(param.getFilter().isEmpty() ? "" : (String) param.getFilter().getFirst().getValue());
        // ExampleMatcher example = ExampleMatcher
        //         .matchingAny()
        //         .withMatcher("id",
        //                 (matcher) -> matcher.ignoreCase().startsWith())
        //         .withMatcher("name", (matcher) -> matcher.ignoreCase().startsWith());
        // return productRepository.findAll(Example.of(entity, example), newPageable)
        //         .map(this::mapToDto);

        return null;

    }

    public ProductForm getDataById(String id) {
        var dto = new ProductForm();

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return mapToDto(product.get());
        }

        return dto;
    }

    private ProductForm mapToDto(Product product) {
        var dto = new ProductForm();

        dto.setSku(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setMinStock(product.getMinStock());
        dto.setMaxStock(product.getMaxStock());
        dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId().toString() : null);
        dto.setStatus(product.getIsActive().toString());

        return dto;
    }

}
