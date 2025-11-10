package id.putra.wms.module.inventory.mapper;

import id.putra.wms.test.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestContainersConfig.class)
public class InventoryItemMapperTest {

    @Autowired
    private InventoryItemMapper inventoryItemMapper;

    @Test
    void mapperLoads() {
        assertThat(inventoryItemMapper).isNotNull();
    }

}
