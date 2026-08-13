package id.putra.wms.module.inventory.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import id.putra.wms.module.inventory.dto.CategoryDto;
import id.putra.wms.module.inventory.model.entity.Category;

public class CategoryConverterTest {

    @Test
    void whenToDto_thenReturnDto() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("Electronics");
        entity.setDescription("Gadgets and devices");

        CategoryDto dto = CategoryConverter.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Electronics");
        assertThat(dto.getDescription()).isEqualTo("Gadgets and devices");
    }

    @Test
    void whenToEntity_thenReturnEntity() {
        CategoryDto dto = CategoryDto.builder()
                .id(2L)
                .name("Hardware")
                .description("Tools")
                .build();

        Category entity = CategoryConverter.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(2L);
        assertThat(entity.getName()).isEqualTo("Hardware");
        assertThat(entity.getDescription()).isEqualTo("Tools");
    }

    @Test
    void whenNull_thenReturnNull() {
        assertThat(CategoryConverter.toDto(null)).isNull();
        assertThat(CategoryConverter.toEntity(null)).isNull();
    }
}
