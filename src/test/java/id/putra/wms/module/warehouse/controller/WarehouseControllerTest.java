package id.putra.wms.module.warehouse.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import id.putra.wms.shared.helpers.ResponseHelper;

@WebMvcTest(WarehouseController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WarehouseCoreService warehouseCoreService;

    @MockBean
    private ResponseHelper responseHelper;

    private WarehouseDto warehouseDto;
    private List<WarehouseDto> warehouseDtos;

    @BeforeEach
    public void setup() {
        warehouseDto = WarehouseDto.builder()
                .id("wh-1")
                .name("Warehouse 1")
                .location("Location 1")
                .build();

        warehouseDtos = List.of(warehouseDto);
    }

    @Test
    void testAddWarehouse() throws Exception {
        mockMvc.perform(post("/api/v1/master-data/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(warehouseDtos)))
                .andExpect(status().isOk());

        verify(warehouseCoreService, times(1)).save(any());
    }

    @Test
    void testUpdateWarehouse() throws Exception {
        mockMvc.perform(patch("/api/v1/master-data/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(warehouseDtos)))
                .andExpect(status().isOk());

        verify(warehouseCoreService, times(1)).update(any());
    }

    @Test
    void testDeleteWarehouse() throws Exception {
        mockMvc.perform(delete("/api/v1/master-data/warehouse")
                .param("id", "wh-1"))
                .andExpect(status().isOk());

        verify(warehouseCoreService, times(1)).delete(any());
    }

    @Test
    void testGetWarehouses() throws Exception {
        Page<WarehouseDto> page = new PageImpl<>(warehouseDtos);
        when(warehouseCoreService.getWarehouses(any(WarehouseDto.class), any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/api/v1/master-data/warehouse")
                .param("search", "Warehouse"))
                .andExpect(status().isOk());

        verify(warehouseCoreService, times(1)).getWarehouses(any(WarehouseDto.class), any(Pageable.class));
    }

    @Test
    void testGetDetailWarehouse() throws Exception {
        when(warehouseCoreService.getWarehouse(any(WarehouseDto.class)))
                .thenReturn(warehouseDto);

        mockMvc.perform(get("/api/v1/master-data/warehouse/{id}", "wh-1"))
                .andExpect(status().isOk());

        verify(warehouseCoreService, times(1)).getWarehouse(any(WarehouseDto.class));
    }
}
