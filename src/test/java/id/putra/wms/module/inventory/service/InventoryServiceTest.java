package id.putra.wms.module.inventory.service;

import id.putra.wms.module.inventory.service.core.InventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    private InventoryService inventoryService;

    @Test
    void serviceMockLoads() {
        assertThat(inventoryService).isNotNull();
    }

}
