package id.putra.wms.module.supplier.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductSupplierIdMapperTest {

    private ProductSupplierIdMapper productSupplierIdMapper = Mappers.getMapper(ProductSupplierIdMapper.class);

    @Test
    void mapperLoads() {
        assertThat(productSupplierIdMapper).isNotNull();
    }

}
