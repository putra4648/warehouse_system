package id.putra.wms.module.inbound.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PurchaseOrderRepositoryTest {

    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(purchaseOrderRepository).isNotNull();
    }

}
