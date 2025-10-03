package id.putra.wms.module.inventory.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.inventory.model.entity.Product;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = true)
public class ProductRepositoryTest implements PostgreSQLContainerInitializer {

    @Autowired
    private ProductRepository productRepository;

    private Product entity;

    @BeforeEach
    public void setup() {
        entity = new Product();
        entity.setId("SKU-001");
        entity.setName("Product 1");
        entity.setDescription("Description 1");
        entity.setMinStock(10L);
        entity.setMaxStock(100L);
        entity.setIsActive(true);

        productRepository.saveAndFlush(entity);
    }

    @AfterEach
    public void tearDown() {
        productRepository.delete(entity);
    }

    @Test
    void givenProduct_whenCreate_shouldReturnListData() {
        var products = productRepository.findAll();

        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    void givenProduct_whenSearchById_shouldReturnData() {
        var product = productRepository.findById("SKU-001");

        assertThat(product).isPresent();
        assertThat(product.get().getName()).isEqualTo("Product 1");
        assertThat(product.get().getMinStock()).isEqualTo(10L);
    }

    @Test
    void givenProduct_whenUpdated_shouldReturnUpdatedEntity() {
        var product = productRepository.findById("SKU-001");
        assertThat(product).isPresent();

        var updatedEntity = product.get();
        updatedEntity.setName("Updated Product");
        updatedEntity.setMinStock(20L);
        productRepository.save(updatedEntity);

        var result = productRepository.findById("SKU-001");
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Updated Product");
        assertThat(result.get().getMinStock()).isEqualTo(20L);
    }

    @Test
    void givenProduct_whenDeleted_shouldReturnEmptyData() {
        productRepository.deleteById("SKU-001");

        var result = productRepository.findById("SKU-001");
        assertThat(result).isEmpty();
    }
}
