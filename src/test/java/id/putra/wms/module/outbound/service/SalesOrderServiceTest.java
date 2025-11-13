package id.putra.wms.module.outbound.service;

import id.putra.wms.module.outbound.service.core.SalesOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SalesOrderServiceTest {

    @Mock
    private SalesOrderService salesOrderService;

    @Test
    void serviceMockLoads() {
        assertThat(salesOrderService).isNotNull();
    }

}
