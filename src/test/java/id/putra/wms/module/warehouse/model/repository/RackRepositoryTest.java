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
import id.putra.wms.module.warehouse.model.entity.Rack;
import id.putra.wms.module.warehouse.model.entity.Zone;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = true)
public class RackRepositoryTest extends PostgreSQLContainerInitializer {
    @Autowired
    private RackRepository rackRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    private Rack entity;
    private List<Rack> racks;
    private Zone zone;

    @BeforeEach
    public void setup() {
        racks = new ArrayList<>();

        zone = new Zone();
        zone.setId("zone-1");

        entity = new Rack();
        entity.setId("rack-1");
        entity.setName("Rack 1");
        entity.setZone(zone);

        racks.add(entity);

        entity = new Rack();
        entity.setId("rack-2");
        entity.setName("Rack 2");
        entity.setZone(zone);

        racks.add(entity);

        zone.setRacks(racks);

        zoneRepository.save(zone);

    }

    @AfterEach
    public void tearDown() {
        rackRepository.delete(entity);
    }

    @Test
    void givenRack_whenCreate_shouldReturnListData() {
        var r = rackRepository.findAll();

        assertThat(r).isNotNull();
        assertThat(r.size()).isNotNull().isGreaterThan(1);
    }

    @Test
    void givenRack_whenSearch_shouldReturnData() {
        var r = rackRepository.findById("rack-1");

        assertThat(r.isPresent()).isTrue();
        assertThat(r.get().getName()).isEqualTo("Rack 1");

        var z = r.get().getZone();

        assertThat(z).isNotNull();
        assertThat(z.getId()).isEqualTo("zone-1");
    }

    @Test
    void givenWarehouse_whenSearch_shouldReturnData() {
        var r = rackRepository.findById("rack-1");

        assertThat(r.isPresent()).isTrue();
        assertThat(r.get().getName()).isEqualTo("Rack 1");

        var z = r.get().getZone();

        assertThat(z).isNotNull();
        assertThat(z.getId()).isEqualTo("zone-1");
    }

    @Test
    void givenRack_whenUpdated_shouldReturnUpdatedEntity() {
        var r = rackRepository.findById("rack-1");

        assertThat(r).isNotNull();

        var updatedEntity = r.get();
        updatedEntity.setName("Rack 2");
        rackRepository.save(updatedEntity);

        Optional<Rack> newR = rackRepository.findById("rack-1");

        assertThat(newR.get().getName()).isEqualTo("Rack 2");
    }

    @Test
    void givenRack_whenDeleted_shouldReturnEmptyData() {
        // Assume page = 1, size per page = 20

        rackRepository.deleteById("rack-1");
        var zn = rackRepository.findById("rack-1");

        assertThat(zn).isEmpty();

    }
}
