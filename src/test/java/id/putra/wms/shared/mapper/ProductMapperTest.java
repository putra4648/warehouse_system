package id.putra.wms.shared.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductMapperTest {

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void mapperLoads() {
        assertThat(productMapper).isNotNull();
    }

}
