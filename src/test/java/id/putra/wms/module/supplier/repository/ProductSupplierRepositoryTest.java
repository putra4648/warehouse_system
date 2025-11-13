package id.putra.wms.module.supplier.repository;

import id.putra.wms.module.supplier.model.repository.ProductSupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductSupplierRepositoryTest {

    @Mock
    private ProductSupplierRepository productSupplierRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(productSupplierRepository).isNotNull();
    }

}
