package id.putra.wms.module.outbound.service.adapter.command;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.putra.wms.module.outbound.dto.PickingTaskDto;
import id.putra.wms.module.outbound.mapper.PickingTaskMapper;
import id.putra.wms.module.outbound.model.repository.PickingTaskRepository;
import id.putra.wms.module.outbound.service.adapter.command.impl.PickingTaskCommandAdapterImpl;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
public class PickingTaskCommandAdapterTest {

    @Mock
    private PickingTaskRepository pickingTaskRepository;

    @Mock
    private PickingTaskMapper pickingTaskMapper;

    private PickingTaskCommandAdapter pickingTaskCommandAdapter;

    @BeforeEach
    void setUp() {
        pickingTaskCommandAdapter = new PickingTaskCommandAdapterImpl(pickingTaskRepository, pickingTaskMapper);
    }

    @Test
    void whenAddPickingTask_thenPersisted() {
        PickingTaskDto dto = new PickingTaskDto();

        pickingTaskCommandAdapter.add(List.of(dto));

        verify(pickingTaskRepository).saveAll(anyList());
    }

    @Test
    void whenUpdatePickingTask_thenUpdated() {
        PickingTaskDto dto = new PickingTaskDto();
        dto.setId(1L);

        pickingTaskCommandAdapter.update(List.of(dto));

        verify(pickingTaskRepository).saveAll(anyList());
    }

    @Test
    void whenDeletePickingTask_thenRemoved() {
        PickingTaskDto dto = new PickingTaskDto();
        dto.setId(1L);

        pickingTaskCommandAdapter.delete(List.of(dto));

        verify(pickingTaskRepository).deleteAllById(anyIterable());
    }

}
