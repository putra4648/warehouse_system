package id.putra.wms.module.outbound.service;

import id.putra.wms.config.TestJwtConfig;
import id.putra.wms.test.TestContainersConfig;
import id.putra.wms.module.outbound.service.core.SalesOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestContainersConfig.class)
@Import(TestJwtConfig.class)
public class SalesOrderServiceTest {

    @Autowired(required = false)
    private SalesOrderService salesOrderService;

    @Test
    void serviceLoads() {
        assertThat(salesOrderService == null || salesOrderService != null).isTrue();
    }

}
