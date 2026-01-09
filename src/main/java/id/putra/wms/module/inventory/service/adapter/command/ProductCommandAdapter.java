package id.putra.wms.module.inventory.service.adapter.command;

import java.util.List;

import id.putra.wms.shared.base.dto.ProductDto;

public interface ProductCommandAdapter {

    void save(List<ProductDto> dtos);

    void update(List<ProductDto> dtos);

    void delete(List<ProductDto> dtos);

}
