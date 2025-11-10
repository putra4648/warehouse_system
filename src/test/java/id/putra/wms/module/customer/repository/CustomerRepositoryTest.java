package id.putra.wms.module.customer.repository;

import id.putra.wms.test.TestContainersConfig;
import id.putra.wms.module.customer.model.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestContainersConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void repositoryLoads() {
        assertThat(customerRepository).isNotNull();
        assertThat(customerRepository.count()).isGreaterThanOrEqualTo(0L);
    }

}
