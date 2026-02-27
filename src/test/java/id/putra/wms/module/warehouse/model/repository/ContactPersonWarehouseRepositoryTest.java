package id.putra.wms.module.warehouse.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.warehouse.model.entity.ContactPersonWarehouse;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = true)
public class ContactPersonWarehouseRepositoryTest extends PostgreSQLContainerInitializer {

    @Autowired
    private ContactPersonWarehouseRepository contactPersonWarehouseRepository;

    private ContactPersonWarehouse entity;

    @BeforeEach
    public void setup() {
        entity = new ContactPersonWarehouse();
        entity.setName("Contact Person 1");
        entity.setEmail("cp1@example.com");
        entity.setPhone("1234567890");
        entity.setAddress("123 Main St");

        contactPersonWarehouseRepository.saveAndFlush(java.util.Objects.requireNonNull(entity));
    }

    @AfterEach
    public void tearDown() {
        contactPersonWarehouseRepository.delete(java.util.Objects.requireNonNull(entity));
    }

    @Test
    void givenContactPerson_whenCreate_shouldReturnListData() {
        var contactPersons = contactPersonWarehouseRepository.findAll();

        assertThat(contactPersons).isNotNull();
        assertThat(contactPersons.size()).isGreaterThan(0);
    }

    @Test
    void givenContactPerson_whenSearchById_shouldReturnData() {
        var contactPerson = contactPersonWarehouseRepository.findById(entity.getId());

        assertThat(contactPerson).isPresent();
        assertThat(contactPerson.get().getName()).isEqualTo("Contact Person 1");
        assertThat(contactPerson.get().getEmail()).isEqualTo("cp1@example.com");
    }

    @Test
    void givenContactPerson_whenUpdated_shouldReturnUpdatedEntity() {
        var contactPerson = contactPersonWarehouseRepository.findById(entity.getId());
        assertThat(contactPerson).isPresent();

        var updatedEntity = contactPerson.get();
        updatedEntity.setName("Updated Contact Person");
        contactPersonWarehouseRepository.save(updatedEntity);

        var result = contactPersonWarehouseRepository.findById(entity.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Updated Contact Person");
    }

    @Test
    void givenContactPerson_whenDeleted_shouldReturnEmptyData() {
        contactPersonWarehouseRepository.deleteById(entity.getId());

        var result = contactPersonWarehouseRepository.findById(entity.getId());
        assertThat(result).isEmpty();
    }
}
