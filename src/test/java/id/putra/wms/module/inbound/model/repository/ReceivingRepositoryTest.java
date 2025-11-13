package id.putra.wms.module.inbound.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReceivingRepositoryTest {

    @Mock
    private ReceivingRepository receivingRepository;

    @Mock
    private ReceivingLineRepository receivingLineRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(receivingRepository).isNotNull();
        assertThat(receivingLineRepository).isNotNull();
    }

}
