package id.putra.wms.module.inventory.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryMapperTest {

    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void mapperLoads() {
        assertThat(categoryMapper).isNotNull();
    }

}
