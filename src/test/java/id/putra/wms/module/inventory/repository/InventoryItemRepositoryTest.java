package id.putra.wms.module.inventory.repository;

import id.putra.wms.module.inventory.model.repository.InventoryItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class InventoryItemRepositoryTest {

    @Mock
    private InventoryItemRepository inventoryItemRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(inventoryItemRepository).isNotNull();
    }

}
