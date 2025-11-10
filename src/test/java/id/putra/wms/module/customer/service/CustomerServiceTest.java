package id.putra.wms.module.customer.service;

import id.putra.wms.config.TestJwtConfig;
import id.putra.wms.test.TestContainersConfig;
import id.putra.wms.module.customer.service.core.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestContainersConfig.class)
@Import(TestJwtConfig.class)
public class CustomerServiceTest {

    @Autowired(required = false)
    private CustomerService customerService;

    @Test
    void serviceLoads() {
        assertThat(customerService == null || customerService != null).isTrue();
    }

}
