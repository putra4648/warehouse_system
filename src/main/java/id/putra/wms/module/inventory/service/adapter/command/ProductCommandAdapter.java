package id.putra.wms.module.inventory.service.adapter.command;

import id.putra.wms.shared.base.dto.ProductDto;

public interface ProductCommandAdapter {

    ProductDto save(ProductDto dto);

    ProductDto update(ProductDto dto);

    Boolean delete(Long id);

}
