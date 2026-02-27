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

import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.service.adapter.command.ZoneCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.ZoneQueryAdapter;
import id.putra.wms.module.warehouse.service.core.impl.ZoneServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ZoneServiceImplTest {

    @Mock
    private ZoneQueryAdapter zoneQueryAdapter;

    @Mock
    private ZoneCommandAdapter zoneCommandAdapter;

    @InjectMocks
    private ZoneServiceImpl zoneService;

    private ZoneDto zoneDto;
    private List<ZoneDto> zoneDtos;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        zoneDto = ZoneDto.builder()
                .id(1L)
                .name("Zone 1")
                .build();

        zoneDtos = List.of(zoneDto);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void testGetZones() {
        List<ZoneDto> safeZoneDtos = java.util.Objects.requireNonNull(zoneDtos);
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        Page<ZoneDto> expectedPage = new PageImpl<>(safeZoneDtos, safePageable, 1);
        when(zoneQueryAdapter.getZones(any(ZoneDto.class), eq(pageable)))
                .thenReturn(expectedPage);

        Page<ZoneDto> result = zoneService.getZones(zoneDto, pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo(1L);
        verify(zoneQueryAdapter, times(1)).getZones(any(ZoneDto.class), eq(pageable));
    }

    @Test
    void testGetZone() {
        when(zoneQueryAdapter.getZone(zoneDto)).thenReturn(zoneDto);

        ZoneDto result = zoneService.getZone(zoneDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(zoneQueryAdapter, times(1)).getZone(zoneDto);
    }

    @Test
    void testAdd() {
        zoneService.add(zoneDtos);

        verify(zoneCommandAdapter, times(1)).add(zoneDtos);
    }

    @Test
    void testUpdate() {
        zoneService.update(zoneDtos);

        verify(zoneCommandAdapter, times(1)).update(zoneDtos);
    }

    @Test
    void testDelete() {
        zoneService.delete(zoneDtos);

        verify(zoneCommandAdapter, times(1)).delete(zoneDtos);
    }
}
