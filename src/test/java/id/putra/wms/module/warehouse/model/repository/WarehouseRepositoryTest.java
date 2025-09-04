package id.putra.wms.module.warehouse.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.model.entity.ContactPersonWarehouse;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.module.warehouse.model.entity.Zone;
import jakarta.persistence.criteria.Predicate;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = true)
@Transactional
public class WarehouseRepositoryTest implements PostgreSQLContainerInitializer {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ContactPersonWarehouseRepository contactPersonWarehouseRepository;

    private Warehouse entity;
    private List<Zone> zones;
    private ContactPersonWarehouse cp;

    @BeforeEach
    public void setup() {
        entity = new Warehouse();

        zones = new ArrayList<Zone>();
        var z = new Zone();
        z.setId("zone-1");
        z.setName("Zone 1");
        zones.add(z);
        z.setWarehouse(entity);

        cp = new ContactPersonWarehouse();
        cp.setId("contact-1");
        cp.setName("Contact 1");
        cp.setWarehouses(List.of(entity));

        entity.setId("wh-1");
        entity.setZones(zones);
        // entity.setContactPersonWarehouse(cp);
        warehouseRepository.saveAndFlush(entity);
    }

    @AfterEach
    public void tearDown() {
        warehouseRepository.delete(entity);
        contactPersonWarehouseRepository.delete(cp);
        zoneRepository.deleteAll(zones);
    }

    @Test
    void givenWarehouse_whenCreated_shouldReturnListWarehouse() {
        var warehouses = warehouseRepository.findAll();

        assertThat(warehouses).isNotNull();
        assertThat(warehouses.size()).isNotNull().isGreaterThan(0);
    }

    @Test
    void givenWarehouse_whenUpdated_shouldReturnUpdatedEntity() {
        Optional<Warehouse> wh = warehouseRepository.findById("wh-1");

        assertThat(wh).isNotNull();

        var updatedEntity = wh.get();
        updatedEntity.setName("Warehouse 2");
        warehouseRepository.saveAndFlush(updatedEntity);

        Optional<Warehouse> newWh = warehouseRepository.findById("wh-1");

        assertThat(newWh).isNotEmpty();
        assertThat(newWh.get().getUpdatedDate()).isNotNull();
        assertThat(newWh.get().getName()).isEqualTo("Warehouse 2");
    }

    @Test
    void givenWarehouse_whenReadByZones_shouldReturnZonesDataSize() {
        Optional<Warehouse> wh = warehouseRepository.findById("wh-1");

        assertThat(wh).isNotNull();
        var zones = wh.get().getZones();

        assertThat(zones.size()).isNotNull().isGreaterThan(0);
        assertThat(zones.get(0).getName()).isEqualTo("Zone 1");
    }

    @Test
    void givenWarehouse_whenDeleted_shouldReturnEmpty() {
        assertThat(entity).isNotNull();
        warehouseRepository.delete(entity);

        Optional<Warehouse> wh = warehouseRepository.findById("wh-1");

        assertThat(wh).isEmpty();
    }

    @Test
    void givenWarehouse_whenSearchByDto_shouldReturnPaginationResult() {
        // Assume page = 1, size per page = 20
        Pageable pageable = PageRequest.of(0, 20);

        // When use search by id e.g 'wh-1' should return 1 data
        WarehouseDto dto = WarehouseDto.builder().id("wh-1").build();

        assertThat(dto.getId()).isNotNull();

        Specification<Warehouse> byId = (root, criteria, builder) -> {
            Predicate id = builder.like(root.get("id"), "%" + dto.getId() != null ? dto.getId() : "" + "%");
            return id;

        };
        Page<Warehouse> whPage = warehouseRepository.findAll(byId, pageable);

        assertThat(whPage.getSize()).isEqualTo(20);
        assertThat(whPage.getNumber()).isEqualTo(0);
        assertThat(whPage.getContent()).isNotNull();
        assertThat(whPage.getContent().size()).isGreaterThan(0);

    }

    @Test
    void givenWarehouse_whenSearchByDtoButNotExist_shouldReturnPaginationResultWithEmptyList() {
        // Assume page = 1, size per page = 20
        Pageable pageable = PageRequest.of(0, 20);

        // When use search by id e.g 'zzzzz' should return 0 data
        // because not existed
        WarehouseDto dto = WarehouseDto.builder().id("zzzzz").build();

        assertThat(dto.getId()).isNotNull();

        Specification<Warehouse> byId = (root, criteria, builder) -> {
            Predicate id = builder.like(root.get("id"), "%" + dto.getId() != null ? dto.getId() : "" + "%");
            return builder.and(id);

        };
        Page<Warehouse> whPage = warehouseRepository.findAll(byId, pageable);

        assertThat(whPage.getSize()).isEqualTo(20);
        assertThat(whPage.getNumber()).isEqualTo(0);
        assertThat(whPage.getContent()).isNotNull();
        assertThat(whPage.getContent().size()).isZero();
    }

    @Test
    void givenWarehouse_whenSearchByIdAndExist_shouldReturnDetail() {
        var wh = warehouseRepository.findById("wh-1");

        assertThat(wh).isNotEmpty();
        assertThat(wh.get()).isNotNull();
        assertThat(wh.get().getId()).isEqualTo("wh-1");
    }
}
