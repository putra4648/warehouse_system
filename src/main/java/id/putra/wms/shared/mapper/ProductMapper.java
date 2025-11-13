package id.putra.wms.shared.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import id.putra.wms.module.inventory.mapper.InventoryItemMapper;
import id.putra.wms.shared.base.dto.ProductDto;
import id.putra.wms.shared.base.entity.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {InventoryItemMapper.class})
public interface ProductMapper {
    ProductDto toDto(Product entity);

    Product toEntity(ProductDto dto);
}
