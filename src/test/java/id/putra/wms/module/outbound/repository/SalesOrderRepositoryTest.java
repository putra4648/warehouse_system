package id.putra.wms.module.outbound.repository;

import id.putra.wms.module.outbound.model.repository.SalesOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SalesOrderRepositoryTest {

    @Mock
    private SalesOrderRepository salesOrderRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(salesOrderRepository).isNotNull();
    }

}
