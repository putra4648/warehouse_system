package id.putra.wms.module.outbound.service.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PackingServiceTest {

    @Mock
    private PackingService packingService;

    @Test
    void serviceMockLoads() {
        assertThat(packingService).isNotNull();
    }

}
