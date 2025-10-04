package id.putra.wms.module.warehouse.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.warehouse.model.entity.Location;
import id.putra.wms.module.warehouse.model.entity.Rack;
import id.putra.wms.module.warehouse.model.entity.Zone;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = true)
public class LocationRepositoryTest extends PostgreSQLContainerInitializer {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RackRepository rackRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    private Location entity;
    private List<Location> locations;
    private Rack rack;
    private Zone zone;

    @BeforeEach
    public void setup() {
        zone = new Zone();
        zone.setId("zone-1");
        zone.setName("Zone 1");

        rack = new Rack();
        rack.setId("rack-1");
        rack.setName("Rack 1");
        rack.setZone(zone);

        locations = new ArrayList<>();

        entity = new Location();
        entity.setId("loc-1");
        entity.setName("Location 1");
        entity.setType("STANDARD");
        entity.setBinNumber("BIN-001");
        entity.setIsActive(true);
        entity.setRack(rack);

        locations.add(entity);

        var loc2 = new Location();
        loc2.setId("loc-2");
        loc2.setName("Location 2");
        loc2.setType("STANDARD");
        loc2.setBinNumber("BIN-002");
        loc2.setIsActive(true);
        loc2.setRack(rack);

        locations.add(loc2);

        zoneRepository.save(zone);
        rackRepository.save(rack);
        locationRepository.saveAll(locations);
    }

    @AfterEach
    public void tearDown() {
        locationRepository.deleteAll(locations);
    }

    @Test
    void givenLocation_whenCreate_shouldReturnListData() {
        var locs = locationRepository.findAll();

        assertThat(locs).isNotNull();
        assertThat(locs.size()).isGreaterThan(1);
    }

    @Test
    void givenLocation_whenSearchById_shouldReturnData() {
        var loc = locationRepository.findById("loc-1");

        assertThat(loc).isPresent();
        assertThat(loc.get().getName()).isEqualTo("Location 1");
        assertThat(loc.get().getBinNumber()).isEqualTo("BIN-001");

        var r = loc.get().getRack();
        assertThat(r).isNotNull();
        assertThat(r.getId()).isEqualTo("rack-1");
    }

    @Test
    void givenLocation_whenUpdated_shouldReturnUpdatedEntity() {
        var loc = locationRepository.findById("loc-1");
        assertThat(loc).isPresent();

        var updatedEntity = loc.get();
        updatedEntity.setName("Updated Location");
        updatedEntity.setIsActive(false);
        locationRepository.save(updatedEntity);

        var result = locationRepository.findById("loc-1");
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Updated Location");
        assertThat(result.get().getIsActive()).isFalse();
    }

    @Test
    void givenLocation_whenDeleted_shouldReturnEmptyData() {
        locationRepository.deleteById("loc-1");

        var result = locationRepository.findById("loc-1");
        assertThat(result).isEmpty();
    }
}
