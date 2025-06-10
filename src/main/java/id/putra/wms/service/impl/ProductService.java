package id.putra.wms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import id.putra.wms.dto.ProductDto;
import id.putra.wms.entity.Product;
import id.putra.wms.exceptions.ProductException;
import id.putra.wms.repository.ProductRepository;
import id.putra.wms.service.CRUDService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements CRUDService<ProductDto, ProductException> {

    private final ProductRepository productRepository;

    @Override
    @Transactional(rollbackOn = { Exception.class, ProductException.class })
    public void add(ProductDto dto) {
        if (productRepository.existsById(dto.getSku())) {
            throw new ProductException("Product already exist");
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
    public List<ProductDto> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void update(ProductDto form) throws ProductException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(ProductDto form) throws ProductException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
