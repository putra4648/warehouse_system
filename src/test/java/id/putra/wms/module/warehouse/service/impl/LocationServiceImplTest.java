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

import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.service.adapter.command.LocationCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.LocationQueryAdapter;

@ExtendWith(MockitoExtension.class)
public class LocationServiceImplTest {

    @Mock
    private LocationQueryAdapter locationQueryAdapter;

    @Mock
    private LocationCommandAdapter locationCommandAdapter;

    @InjectMocks
    private LocationServiceImpl locationService;

    private LocationDto locationDto;
    private List<LocationDto> locationDtos;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        locationDto = LocationDto.builder()
                .id("loc-1")
                .name("Location 1")
                .type("STANDARD")
                .build();

        locationDtos = List.of(locationDto);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void testGetLocations() {
        List<LocationDto> safeLocationDtos = java.util.Objects.requireNonNull(locationDtos);
        Pageable safePageable = java.util.Objects.requireNonNull(pageable);
        Page<LocationDto> expectedPage = new PageImpl<>(safeLocationDtos, safePageable, 1);
        when(locationQueryAdapter.getLocations(any(LocationDto.class), eq(pageable)))
                .thenReturn(expectedPage);

        Page<LocationDto> result = locationService.getLocations(locationDto, pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo("loc-1");
        verify(locationQueryAdapter, times(1)).getLocations(any(LocationDto.class), eq(pageable));
    }

    @Test
    void testGetLocation() {
        when(locationQueryAdapter.getLocation(locationDto)).thenReturn(locationDto);

        LocationDto result = locationService.getLocation(locationDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("loc-1");
        verify(locationQueryAdapter, times(1)).getLocation(locationDto);
    }

    @Test
    void testAdd() {
        locationService.add(locationDtos);

        verify(locationCommandAdapter, times(1)).add(locationDtos);
    }

    @Test
    void testUpdate() {
        locationService.update(locationDtos);

        verify(locationCommandAdapter, times(1)).update(locationDtos);
    }

    @Test
    void testDelete() {
        locationService.delete(locationDtos);

        verify(locationCommandAdapter, times(1)).delete(locationDtos);
    }
}
