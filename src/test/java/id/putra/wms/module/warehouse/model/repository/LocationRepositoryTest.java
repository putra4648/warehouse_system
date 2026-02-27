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
import org.springframework.data.jpa.domain.Specification;

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
        zone.setName("Zone 1");

        rack = new Rack();
        rack.setName("Rack 1");
        rack.setZone(zone);

        locations = new ArrayList<>();

        entity = new Location();
        entity.setName("Location 1");
        entity.setType("STANDARD");
        entity.setBinNumber("BIN-001");
        entity.setIsActive(true);
        entity.setRack(rack);

        locations.add(entity);

        var loc2 = new Location();
        loc2.setName("Location 2");
        loc2.setType("STANDARD");
        loc2.setBinNumber("BIN-002");
        loc2.setIsActive(true);
        loc2.setRack(rack);

        locations.add(loc2);

        zoneRepository.save(java.util.Objects.requireNonNull(zone));
        rackRepository.save(java.util.Objects.requireNonNull(rack));
        locationRepository.saveAll(java.util.Objects.requireNonNull(locations));
    }

    @AfterEach
    public void tearDown() {
        locationRepository.deleteAll(java.util.Objects.requireNonNull(locations));
    }

    @Test
    void givenLocation_whenCreate_shouldReturnListData() {
        var locs = locationRepository.findAll();

        assertThat(locs).isNotNull();
        assertThat(locs.size()).isGreaterThan(1);
    }

    @Test
    void givenLocation_whenSearchByName_shouldReturnData() {
        Specification<Location> spec = (root, cr, cb) -> cb.equal(root.get("name"), "Location 1");
        var loc = locationRepository.findAll(spec);

        assertThat(loc).isNotNull();
        assertThat(loc.isEmpty()).isFalse();
        assertThat(loc.size()).isGreaterThan(0);
    }

    @Test
    void givenLocation_whenUpdated_shouldReturnUpdatedEntity() {
        var loc = locationRepository.findById(entity.getId());
        assertThat(loc).isPresent();

        var updatedEntity = loc.get();
        updatedEntity.setName("Updated Location");
        updatedEntity.setIsActive(false);
        locationRepository.save(updatedEntity);

        var result = locationRepository.findById(entity.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Updated Location");
        assertThat(result.get().getIsActive()).isFalse();
    }

    @Test
    void givenLocation_whenDeleted_shouldReturnEmptyData() {
        locationRepository.deleteById(entity.getId());

        var result = locationRepository.findById(entity.getId());
        assertThat(result).isEmpty();
    }
}
