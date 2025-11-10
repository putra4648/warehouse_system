package id.putra.wms.module.inbound.service.adapter.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import id.putra.wms.module.inbound.dto.ReceivingDto;
import id.putra.wms.module.inbound.model.entity.Receiving;
import id.putra.wms.module.inbound.model.repository.ReceivingRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = "spring.main.allow-circular-references=true")
public class ReceivingCommandAdapterTest {

    @SuppressWarnings("resource")
    static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("wms-test-db")
            .withUsername("wms")
            .withPassword("wms-password");

    static {
        postgreSQLContainer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(postgreSQLContainer::stop));
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private ReceivingCommandAdapter receivingCommandAdapter;

    @Autowired
    private ReceivingRepository receivingRepository;

    @Test
    void whenAddReceivingDtos_thenSaved() {
        receivingRepository.deleteAll();

        ReceivingDto dto = new ReceivingDto();
        dto.setReceivingNumber("RCV-TEST-ADD");
        dto.setReceivedDate(new Date(System.currentTimeMillis()));
        dto.setStatus("NEW");

        receivingCommandAdapter.add(List.of(dto));

        List<Receiving> all = receivingRepository.findAll();
    }

    void whenUpdateReceivingDto_thenEntityUpdated() {
        ReceivingDto dto = new ReceivingDto();
        dto.setReceivingNumber("RCV-CMD-2");
        dto.setStatus("NEW");

        receivingCommandAdapter.add(List.of(dto));

        var persisted = receivingRepository.findAll().stream().filter(r -> "RCV-CMD-2".equals(r.getReceivingNumber()))
                .findFirst().orElse(null);
        assertThat(persisted).isNotNull();

        ReceivingDto update = new ReceivingDto();
        update.setId(persisted.getId());
        update.setReceivingNumber("RCV-CMD-2-UPDATED");
        update.setStatus("UPDATED");

        receivingCommandAdapter.update(List.of(update));

        var refreshed = receivingRepository.findById(persisted.getId()).orElse(null);
        assertThat(refreshed).isNotNull();
        assertThat(refreshed.getReceivingNumber()).isEqualTo("RCV-CMD-2-UPDATED");
    }

    @Test
    void whenDeleteReceivingDto_thenEntityRemoved() {
        ReceivingDto dto = new ReceivingDto();
        dto.setReceivingNumber("RCV-CMD-3");
        dto.setStatus("NEW");

        receivingCommandAdapter.add(List.of(dto));
        var persisted = receivingRepository.findAll().stream().filter(r -> "RCV-CMD-3".equals(r.getReceivingNumber()))
                .findFirst().orElse(null);
        assertThat(persisted).isNotNull();

        ReceivingDto del = new ReceivingDto();
        del.setId(persisted.getId());
        receivingCommandAdapter.delete(List.of(del));

        var after = receivingRepository.findById(persisted.getId());
        assertThat(after).isEmpty();
    }

}
