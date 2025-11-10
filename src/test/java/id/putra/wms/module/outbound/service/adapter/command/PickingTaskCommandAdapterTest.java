package id.putra.wms.module.outbound.service.adapter.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import id.putra.wms.PostgreSQLContainerInitializer;
import id.putra.wms.module.outbound.dto.PickingTaskDto;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = true)
public class PickingTaskCommandAdapterTest extends PostgreSQLContainerInitializer {

    @Autowired
    private PickingTaskCommandAdapter pickingTaskCommandAdapter;

    @Autowired
    private id.putra.wms.module.outbound.model.repository.PickingTaskRepository pickingTaskRepository;

    @Test
    void whenAddPickingTask_thenPersisted() {
    PickingTaskDto dto = new PickingTaskDto();
    dto.setSalesOrderLine(null);

        pickingTaskCommandAdapter.add(List.of(dto));

        var all = pickingTaskRepository.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void whenUpdatePickingTask_thenUpdated() {
    PickingTaskDto dto = new PickingTaskDto();
    dto.setSalesOrderLine(null);
        pickingTaskCommandAdapter.add(List.of(dto));

        var persisted = pickingTaskRepository.findAll().stream().findFirst().orElse(null);
        assertThat(persisted).isNotNull();

    PickingTaskDto update = new PickingTaskDto();
    update.setId(persisted.getId());
    update.setSalesOrderLine(null);
        pickingTaskCommandAdapter.update(List.of(update));

        var refreshed = pickingTaskRepository.findById(persisted.getId()).orElse(null);
        assertThat(refreshed).isNotNull();
    }

    @Test
    void whenDeletePickingTask_thenRemoved() {
    PickingTaskDto dto = new PickingTaskDto();
    dto.setSalesOrderLine(null);
        pickingTaskCommandAdapter.add(List.of(dto));

        var persisted = pickingTaskRepository.findAll().stream().findFirst().orElse(null);
        assertThat(persisted).isNotNull();

        PickingTaskDto del = new PickingTaskDto();
        del.setId(persisted.getId());
        pickingTaskCommandAdapter.delete(List.of(del));

        var after = pickingTaskRepository.findById(persisted.getId());
        assertThat(after).isEmpty();
    }

}
