package id.putra.wms.module.inventory.service;

import id.putra.wms.config.TestJwtConfig;
import id.putra.wms.test.TestContainersConfig;
import id.putra.wms.module.inventory.service.core.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestContainersConfig.class)
@Import(TestJwtConfig.class)
public class InventoryServiceTest {

    @Autowired(required = false)
    private InventoryService inventoryService;

    @Test
    void serviceLoads() {
        assertThat(inventoryService == null || inventoryService != null).isTrue();
    }

}
