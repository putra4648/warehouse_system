package id.putra.wms.module.customer.mapper;

import id.putra.wms.test.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestContainersConfig.class)
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void mapperLoads() {
        assertThat(customerMapper).isNotNull();
    }

}
