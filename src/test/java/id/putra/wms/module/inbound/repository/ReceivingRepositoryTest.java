package id.putra.wms.module.inbound.repository;

import id.putra.wms.module.inbound.model.repository.ReceivingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ReceivingRepositoryTest {

    @Mock
    private ReceivingRepository receivingRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(receivingRepository).isNotNull();
    }

}
