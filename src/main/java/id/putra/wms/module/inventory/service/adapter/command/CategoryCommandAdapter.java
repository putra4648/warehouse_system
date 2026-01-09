package id.putra.wms.module.inventory.service.adapter.command;

import java.util.List;

import id.putra.wms.module.inventory.dto.CategoryDto;

public interface CategoryCommandAdapter {

    void save(List<CategoryDto> dtos);

    void update(List<CategoryDto> dtos);

    void delete(List<CategoryDto> dtos);

}
