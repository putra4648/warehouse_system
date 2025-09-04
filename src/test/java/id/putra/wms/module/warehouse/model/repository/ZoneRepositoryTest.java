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

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.module.warehouse.model.entity.Zone;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = true)
public class ZoneRepositoryTest implements PostgreSQLContainerInitializer {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    private Warehouse entity;
    private List<Zone> zones;

    @BeforeEach
    public void setup() {
        entity = new Warehouse();

        zones = new ArrayList<Zone>();
        var z = new Zone();
        z.setId("zone-1");
        z.setName("Zone 1");
        zones.add(z);
        z.setWarehouse(entity);

        entity.setId("wh-1");
        entity.setName("Warehouse 1");
        entity.setZones(zones);

        warehouseRepository.save(entity);
    }

    @AfterEach
    public void tearDown() {
        zoneRepository.deleteAll(zones);
    }

    @Test
    void givenZone_whenCreate_shouldReturnListData() {
        var zn = zoneRepository.findAll();

        assertThat(zn).isNotNull();
        assertThat(zn.size()).isNotNull().isGreaterThan(0);
    }

    @Test
    void givenZone_whenSearchById_shouldReturnData() {
        var zn = zoneRepository.findById("zone-1");

        assertThat(zn.isPresent()).isTrue();
        assertThat(zn.get().getName()).isEqualTo("Zone 1");

        var wh = zn.get().getWarehouse();
        assertThat(wh).isNotNull();
        assertThat(wh.getName()).isEqualTo("Warehouse 1");
    }

    @Test
    void givenZone_whenUpdated_shouldReturnUpdatedEntity() {
        var zn = zoneRepository.findById("zone-1");

        assertThat(zn).isNotNull();

        var updatedEntity = zn.get();
        updatedEntity.setName("Zone 2");
        zoneRepository.save(updatedEntity);

        Optional<Zone> newWh = zoneRepository.findById("zone-1");

        assertThat(newWh.get().getName()).isEqualTo("Zone 2");
    }

    @Test
    void givenZone_whenDeleted_shouldReturnEmptyData() {
        // Assume page = 1, size per page = 20

        zoneRepository.deleteById("zone-1");
        var zn = zoneRepository.findById("zone-1");

        assertThat(zn).isEmpty();

    }

}
