package id.putra.wms.module.inventory.repository;

import id.putra.wms.module.inventory.model.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(categoryRepository).isNotNull();
    }

}
