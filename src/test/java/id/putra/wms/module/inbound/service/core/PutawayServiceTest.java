package id.putra.wms.module.inbound.service.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PutawayServiceTest {

    @Mock
    private PutawayService putawayService;

    @Test
    void serviceMockLoads() {
        assertThat(putawayService).isNotNull();
    }

}
