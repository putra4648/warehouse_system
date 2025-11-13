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

import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.service.adapter.command.RackCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.RackQueryAdapter;
import id.putra.wms.module.warehouse.service.core.impl.RackServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RackServiceImplTest {

    @Mock
    private RackQueryAdapter rackQueryAdapter;

    @Mock
    private RackCommandAdapter rackCommandAdapter;

    @InjectMocks
    private RackServiceImpl rackService;

    private RackDto rackDto;
    private List<RackDto> rackDtos;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        rackDto = RackDto.builder()
                .id("rack-1")
                .name("Rack 1")
                .build();

        rackDtos = List.of(rackDto);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void testGetRacks() {
        List<RackDto> safeRackDtos = java.util.Objects.requireNonNull(rackDtos);
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        Page<RackDto> expectedPage = new PageImpl<>(safeRackDtos, safePageable, 1);
        when(rackQueryAdapter.getRacks(any(RackDto.class), eq(pageable)))
                .thenReturn(expectedPage);

        Page<RackDto> result = rackService.getRacks(rackDto, pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo("rack-1");
        verify(rackQueryAdapter, times(1)).getRacks(any(RackDto.class), eq(pageable));
    }

    @Test
    void testGetRack() {
        when(rackQueryAdapter.getRackById(rackDto)).thenReturn(rackDto);

        RackDto result = rackService.getRack(rackDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("rack-1");
        verify(rackQueryAdapter, times(1)).getRackById(rackDto);
    }

    @Test
    void testAdd() {
        rackService.add(rackDtos);

        verify(rackCommandAdapter, times(1)).add(rackDtos);
    }

    @Test
    void testUpdate() {
        rackService.update(rackDtos);

        verify(rackCommandAdapter, times(1)).update(rackDtos);
    }

    @Test
    void testDelete() {
        rackService.delete(rackDtos);

        verify(rackCommandAdapter, times(1)).delete(rackDtos);
    }
}
