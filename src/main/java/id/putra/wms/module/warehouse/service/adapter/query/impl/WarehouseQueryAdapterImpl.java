package id.putra.wms.module.warehouse.service.adapter.query.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.putra.wms.config.exception.ModuleException;
import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.mapper.WarehouseMapper;
import id.putra.wms.module.warehouse.model.entity.Rack;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.module.warehouse.model.entity.Zone;
import id.putra.wms.module.warehouse.model.repository.RackRepository;
import id.putra.wms.module.warehouse.model.repository.WarehouseRepository;
import id.putra.wms.module.warehouse.model.repository.ZoneRepository;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import id.putra.wms.shared.enums.ResponseEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseQueryAdapterImpl implements WarehouseQueryAdapter {

    // private final JdbcTemplate jdbcTemplate;
    // private final TaskExecutor virtualThreadExecutor;

    private final WarehouseRepository warehouseRepository;
    private final ZoneRepository zoneRepository;
    private final RackRepository rackRepository;

    private final WarehouseMapper warehouseMapper;

    @Override
    public WarehouseDto getWarehouseById(WarehouseDto dto) {
        Optional<Warehouse> wh = warehouseRepository.findById(dto.getId());
        return warehouseMapper.toWarehouseDto(wh.orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND)));
    }

    @Override
    public Page<ZoneDto> getZonesByWarehouseId(WarehouseDto dto, Pageable pageable) {
        var warehouse = new Warehouse();
        warehouse.setId(dto.getId());
        Page<ZoneDto> zn = zoneRepository.findByWarehouse(warehouse, pageable).map((z) -> warehouseMapper.toZoneDto(z));
        return zn;
    }

    @Override
    public ZoneDto getZoneByIdByWarehouseId(WarehouseDto dto, ZoneDto zoneDto) {
        var warehouse = new Warehouse();
        warehouse.setId(dto.getId());
        Zone zn = zoneRepository.findByWarehouseAndById(warehouse, zoneDto.getId());
        return warehouseMapper.toZoneDto(zn);
    }

    @Override
    public RackDto getLocationByRack(WarehouseDto dto, ZoneDto zoneDto, RackDto rackDto) {
        Optional<Rack> rk = rackRepository.findById(rackDto.getId());
        if (rk.isPresent()) {
            return warehouseMapper.toRackDto(rk.get());
        }

        return null;
    }

    @Override
    public Page<WarehouseDto> getWarehouses(WarehouseDto dto, Pageable pageable) {
        var entity = new Warehouse();
        // entity.setId(param.getFilter().isEmpty() ? "" : (String)
        // param.getFilter().get(0).getValue());
        // entity.setName(param.getFilter().isEmpty() ? "" : (String)
        // param.getFilter().get(0).getValue());
        ExampleMatcher example = ExampleMatcher
                .matchingAny()
                .withMatcher("id",
                        (matcher) -> matcher.ignoreCase().startsWith())
                .withMatcher("name", (matcher) -> matcher.ignoreCase().startsWith());
        return warehouseRepository.findAll(Example.of(entity, example), pageable)
                .map(data -> warehouseMapper.toWarehouseDto(data));
    }

    @Override
    public Page<RackDto> getRacks(WarehouseDto dto, ZoneDto zoneDto, Pageable pageable) {
        return null;
    }

}
