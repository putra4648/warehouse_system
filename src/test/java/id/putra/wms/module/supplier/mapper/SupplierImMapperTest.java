package id.putra.wms.module.supplier.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

public class SupplierImMapperTest {

    private SupplierMapper supplierMapper = Mappers.getMapper(SupplierMapper.class);

    @Test
    void mapperLoads() {
        assertThat(supplierMapper).isNotNull();
    }

}
