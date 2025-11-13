package id.putra.wms.module.inventory.repository;

import id.putra.wms.module.inventory.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(productRepository).isNotNull();
    }

}
