package id.putra.wms.module.warehouse.repository;

import id.putra.wms.module.warehouse.model.repository.RackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RackRepositoryTest {

    @Mock
    private RackRepository rackRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(rackRepository).isNotNull();
    }

}
