package id.putra.wms.module.inbound.service;

import id.putra.wms.module.inbound.service.core.ReceivingOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ReceivingServiceTest {

    @Mock
    private ReceivingOrderService receivingOrderService;

    @Test
    void serviceMockLoads() {
        assertThat(receivingOrderService).isNotNull();
    }

}
