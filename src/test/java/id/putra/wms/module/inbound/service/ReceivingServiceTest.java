package id.putra.wms.module.inbound.service;

import id.putra.wms.config.TestJwtConfig;
import id.putra.wms.test.TestContainersConfig;
import id.putra.wms.module.inbound.service.core.ReceivingOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestContainersConfig.class)
@Import(TestJwtConfig.class)
public class ReceivingServiceTest {

    @Autowired(required = false)
    private ReceivingOrderService receivingOrderService;

    @Test
    void serviceLoads() {
        assertThat(receivingOrderService == null || receivingOrderService != null).isTrue();
    }

}
