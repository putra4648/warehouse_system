package id.putra.wms.module.inbound.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.inbound.model.entity.Receiving;
import id.putra.wms.module.inbound.model.entity.ReceivingLine;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class ReceivingRepositoryTest extends PostgreSQLContainerInitializer {

    @Autowired
    private ReceivingRepository receivingRepository;

    @Autowired
    private ReceivingLineRepository receivingLineRepository;

    private Receiving receiving;
    private ReceivingLine line;

    @BeforeEach
    void setup() {
        receiving = new Receiving();
        receiving.setReceivingNumber("RCV-100");
        receiving.setStatus("NEW");
        receivingRepository.saveAndFlush(java.util.Objects.requireNonNull(receiving));

        line = new ReceivingLine();
        line.setQtyReceived(new java.math.BigDecimal("3"));
        line.setReceiving(receiving);
        receivingLineRepository.saveAndFlush(java.util.Objects.requireNonNull(line));
    }

    @AfterEach
    void tearDown() {
        if (line != null) {
            receivingLineRepository.delete(line);
        }
        if (receiving != null) {
            receivingRepository.delete(receiving);
        }
    }

    @Test
    void whenSaveReceiving_thenFindByIdReturnsIt() {
        Optional<Receiving> found = receivingRepository.findById(receiving.getId());

        assertThat(found).isNotEmpty();
        assertThat(found.get().getReceivingNumber()).isEqualTo("RCV-100");
    }

    @Test
    void whenSaveReceivingLine_thenReceivingHasLines() {
        List<ReceivingLine> lines = receivingLineRepository.findAll();

        assertThat(lines).isNotNull();
        assertThat(lines.size()).isGreaterThan(0);
    }

}
