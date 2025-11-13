package id.putra.wms.module.customer.repository;

import id.putra.wms.module.customer.model.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(customerRepository).isNotNull();
    }

}
