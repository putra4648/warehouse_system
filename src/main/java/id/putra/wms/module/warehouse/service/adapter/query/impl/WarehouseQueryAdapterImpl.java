package id.putra.wms.module.warehouse.service.adapter.query.impl;

import id.putra.wms.module.warehouse.dto.response.WarehouseRes;
import id.putra.wms.module.warehouse.mapper.WarehouseMapper;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class WarehouseQueryAdapterImpl implements WarehouseQueryAdapter {

    private final JdbcTemplate jdbcTemplate;

    private final WarehouseMapper warehouseMapper;

    private final TaskExecutor virtualThreadExecutor;

    @Override
    public Page<WarehouseRes> getAll(Pageable pageable) {
        CompletableFuture<Integer> totalData = CompletableFuture.supplyAsync(this::getTotal, virtualThreadExecutor);
        CompletableFuture<List<Warehouse>> warehouses = CompletableFuture.supplyAsync(() -> {
            String query = "SELECT * FROM warehouse LIMIT ? OFFSET ?";
            return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Warehouse.class), pageable.getPageSize(), pageable.getOffset());
        }, virtualThreadExecutor);

        CompletableFuture.allOf(totalData, warehouses);

        return new PageImpl<>(
                warehouseMapper.toResponses(warehouses.join()),
                pageable,
                totalData.join()
        );
    }

    @Override
    public Optional<WarehouseRes> getById(String id) {
        String query = "SELECT * FROM warehouse WHERE id = ?";
        try {
            Warehouse warehouse = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Warehouse.class), id);
            return Optional.of(warehouseMapper.toResponse(warehouse));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private Integer getTotal() {
        String countQuery = "SELECT count(*) FROM warehouse";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }

}
