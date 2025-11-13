package id.putra.wms.module.supplier.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductSupplierMapperTest {

    private ProductSupplierMapper productSupplierMapper = Mappers.getMapper(ProductSupplierMapper.class);

    @Test
    void mapperLoads() {
        assertThat(productSupplierMapper).isNotNull();
    }

}
