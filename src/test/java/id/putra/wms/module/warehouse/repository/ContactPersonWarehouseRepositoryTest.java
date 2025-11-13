package id.putra.wms.module.warehouse.repository;

import id.putra.wms.module.warehouse.model.repository.ContactPersonWarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ContactPersonWarehouseRepositoryTest {

    @Mock
    private ContactPersonWarehouseRepository contactPersonWarehouseRepository;

    @Test
    void repositoryMockLoads() {
        assertThat(contactPersonWarehouseRepository).isNotNull();
    }

}
