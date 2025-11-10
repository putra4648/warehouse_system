package id.putra.wms.module.supplier.mapper;

import id.putra.wms.test.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestContainersConfig.class)
public class SupplierMapperTest {

    @Autowired
    private SupplierMapper supplierMapper;

    @Test
    void mapperLoads() {
        assertThat(supplierMapper).isNotNull();
    }

}
