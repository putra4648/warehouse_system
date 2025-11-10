package id.putra.wms.module.inventory.repository;

import id.putra.wms.test.TestContainersConfig;
import id.putra.wms.module.inventory.model.repository.InventoryItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestContainersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InventoryItemRepositoryTest {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Test
    void repositoryLoads() {
        assertThat(inventoryItemRepository).isNotNull();
        assertThat(inventoryItemRepository.count()).isGreaterThanOrEqualTo(0L);
    }

}
