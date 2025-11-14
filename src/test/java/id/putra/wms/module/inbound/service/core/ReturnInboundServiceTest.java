package id.putra.wms.module.inbound.service.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReturnInboundServiceTest {

    @Mock
    private ReturnInboundService returnInboundService;

    @Test
    void serviceMockLoads() {
        assertThat(returnInboundService).isNotNull();
    }

}
