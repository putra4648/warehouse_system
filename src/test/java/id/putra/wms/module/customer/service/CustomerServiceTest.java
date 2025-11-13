package id.putra.wms.module.customer.service;

import id.putra.wms.module.customer.service.core.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerService customerService;

    @Test
    void serviceMockLoads() {
        assertThat(customerService).isNotNull();
    }

}
