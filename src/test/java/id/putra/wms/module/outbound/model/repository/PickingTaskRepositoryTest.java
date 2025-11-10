package id.putra.wms.module.outbound.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.outbound.model.entity.PickingTask;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class PickingTaskRepositoryTest extends PostgreSQLContainerInitializer {

    @Autowired
    private PickingTaskRepository pickingTaskRepository;

    private PickingTask task;

    @BeforeEach
    void setup() {
        task = new PickingTask();
        pickingTaskRepository.saveAndFlush(java.util.Objects.requireNonNull(task));
    }

    @AfterEach
    void tearDown() {
        if (task != null) {
            pickingTaskRepository.delete(task);
        }
    }

    @Test
    void whenSavePickingTask_thenFindAllReturnsIt() {
        var all = pickingTaskRepository.findAll();
        assertThat(all).isNotNull();
        assertThat(all.size()).isGreaterThan(0);
    }

}
