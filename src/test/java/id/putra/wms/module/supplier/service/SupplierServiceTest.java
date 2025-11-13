package id.putra.wms.module.supplier.service;

import id.putra.wms.module.supplier.service.core.SupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    private SupplierService supplierService;

    @Test
    void serviceMockLoads() {
        assertThat(supplierService).isNotNull();
    }

}
