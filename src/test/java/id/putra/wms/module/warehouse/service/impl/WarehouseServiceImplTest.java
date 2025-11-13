package id.putra.wms.module.warehouse.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import id.putra.wms.module.warehouse.service.core.impl.WarehouseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class WarehouseServiceImplTest {

    @Mock
    private WarehouseQueryAdapter warehouseQueryAdapter;

    @Mock
    private WarehouseCommandAdapter warehouseCommandAdapter;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    private WarehouseDto warehouseDto;
    private List<WarehouseDto> warehouseDtos;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        warehouseDto = WarehouseDto.builder()
                .id("wh-1")
                .name("Warehouse 1")
                .location("Location 1")
                .build();

        warehouseDtos = List.of(warehouseDto);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void testGetWarehouses() {
        List<WarehouseDto> safeWarehouseDtos = java.util.Objects.requireNonNull(warehouseDtos);
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        Page<WarehouseDto> expectedPage = new PageImpl<>(safeWarehouseDtos, safePageable, 1);
        when(warehouseQueryAdapter.getWarehouses(any(WarehouseDto.class), eq(pageable)))
                .thenReturn(expectedPage);

        Page<WarehouseDto> result = warehouseService.getWarehouses(warehouseDto, pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo("wh-1");
        verify(warehouseQueryAdapter, times(1)).getWarehouses(any(WarehouseDto.class), eq(pageable));
    }

    @Test
    void testGetWarehouse() {
        when(warehouseQueryAdapter.getWarehouseById(warehouseDto)).thenReturn(warehouseDto);

        WarehouseDto result = warehouseService.getWarehouse(warehouseDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("wh-1");
        verify(warehouseQueryAdapter, times(1)).getWarehouseById(warehouseDto);
    }

    @Test
    void testSave() {
        warehouseService.save(warehouseDtos);

        verify(warehouseCommandAdapter, times(1)).save(warehouseDtos);
    }

    @Test
    void testUpdate() {
        warehouseService.update(warehouseDtos);

        verify(warehouseCommandAdapter, times(1)).update(warehouseDtos);
    }

    @Test
    void testDelete() {
        warehouseService.delete(warehouseDtos);

        verify(warehouseCommandAdapter, times(1)).delete(warehouseDtos);
    }
}
