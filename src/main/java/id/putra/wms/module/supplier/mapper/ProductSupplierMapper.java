package id.putra.wms.module.supplier.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import id.putra.wms.module.inventory.model.repository.ProductRepository;
import id.putra.wms.module.supplier.dto.ProductSupplierDto;
import id.putra.wms.module.supplier.model.entity.ProductSupplier;
import id.putra.wms.module.supplier.model.entity.ProductSupplierId;
import id.putra.wms.shared.mapper.ProductMapper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ProductMapper.class })
public abstract class ProductSupplierMapper {

    @Autowired
    protected ProductRepository productRepository;

    public abstract ProductSupplierDto toDto(ProductSupplier entity);

    public abstract ProductSupplier toEntity(ProductSupplierDto dto);

    public abstract List<ProductSupplier> toEntities(List<ProductSupplierDto> dtos);

    @Mapping(target = "product", ignore = true)
    public abstract void updateEntityFromDto(ProductSupplierDto dto, @MappingTarget ProductSupplier entity);

    @AfterMapping
    protected void initIdAndProduct(@MappingTarget ProductSupplier entity, ProductSupplierDto dto) {
        if (entity.getId() == null) {
            entity.setId(new ProductSupplierId());
        }

        if (dto.getProduct() != null && dto.getProduct().getId() != null) {
            entity.getId().setProductId(dto.getProduct().getId());
            entity.setProduct(productRepository.getReferenceById(dto.getProduct().getId()));
        }
    }
}
