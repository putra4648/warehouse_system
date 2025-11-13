package id.putra.wms.module.warehouse.service;

import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {

    @Mock
    private WarehouseCoreService warehouseCoreService;

    @Test
    void serviceMockLoads() {
        assertThat(warehouseCoreService).isNotNull();
    }

}
