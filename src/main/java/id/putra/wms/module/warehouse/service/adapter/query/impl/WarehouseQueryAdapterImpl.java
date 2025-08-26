package id.putra.wms.module.warehouse.service.adapter.query.impl;

import id.putra.wms.config.exception.ModuleException;
import id.putra.wms.module.warehouse.dto.LocationDto;
import id.putra.wms.module.warehouse.dto.RackDto;
import id.putra.wms.module.warehouse.dto.WarehouseDto;
import id.putra.wms.module.warehouse.dto.ZoneDto;
import id.putra.wms.module.warehouse.model.entity.Location;
import id.putra.wms.module.warehouse.model.entity.Rack;
import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.module.warehouse.model.entity.Zone;
import id.putra.wms.module.warehouse.model.repository.RackRepository;
import id.putra.wms.module.warehouse.model.repository.WarehouseRepository;
import id.putra.wms.module.warehouse.model.repository.ZoneRepository;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import id.putra.wms.shared.enums.ResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseQueryAdapterImpl implements WarehouseQueryAdapter {

    // private final JdbcTemplate jdbcTemplate;
    // private final WarehouseMapper warehouseMapper;
    // private final TaskExecutor virtualThreadExecutor;

    private final WarehouseRepository warehouseRepository;
    private final ZoneRepository zoneRepository;
    private final RackRepository rackRepository;

    @Override
    public WarehouseDto getWarehouseById(WarehouseDto dto) {
        Optional<Warehouse> wh = warehouseRepository.findById(dto.getId());
        return mapWarehouseToDto(wh.orElseThrow(() -> new ModuleException(ResponseEnum.DATA_NOT_FOUND)));
    }

    @Override
    public Page<ZoneDto> getZonesByWarehouseId(WarehouseDto dto, Pageable pageable) {
        var warehouse = new Warehouse();
        warehouse.setId(dto.getId());
        Page<ZoneDto> zn = zoneRepository.findByWarehouse(warehouse, pageable).map((z) -> mapZoneToDto(z));
        return zn;
    }

    @Override
    public ZoneDto getZoneByIdByWarehouseId(WarehouseDto dto, ZoneDto zoneDto) {
        var warehouse = new Warehouse();
        warehouse.setId(dto.getId());
        Zone zn = zoneRepository.findByWarehouseAndById(warehouse, zoneDto.getId());
        return mapZoneToDto(zn);
    }

    @Override
    public RackDto getLocationByRack(WarehouseDto dto, ZoneDto zoneDto, RackDto rackDto) {
        Optional<Rack> rk = rackRepository.findById(rackDto.getId());
        if (rk.isPresent()) {
            return mapRackToDto(rk.get());
        }

        return null;
    }

    private WarehouseDto mapWarehouseToDto(Warehouse wh) {
        return WarehouseDto.builder().id(wh.getId()).name(wh.getName()).isActive(wh.getIsActive())
                .location(wh.getLocation()).area(wh.getArea()).total(wh.getTotal())
                .zones(wh.getZones().stream().map(z -> mapZoneToDto(z)).toList()).build();
    }

    private ZoneDto mapZoneToDto(Zone zn) {
        return ZoneDto.builder().id(zn.getId()).code(zn.getCode()).name(zn.getName()).isActive(zn.getIsActive())
                .type(zn.getType()).warehouse(mapWarehouseToDto(zn.getWarehouse()))
                .racks(zn.getRacks() != null ? zn.getRacks().stream().map(r -> mapRackToDto(r)).toList()
                        : Collections.emptyList())
                .build();
    }

    private RackDto mapRackToDto(Rack rk) {
        return RackDto.builder().id(rk.getId()).name(rk.getName()).row(rk.getRows()).col(rk.getCols())
                .locations(rk.getLocations() != null ? rk.getLocations().stream().map(l -> mapLocationToDto(l)).toList()
                        : Collections.emptyList())
                .build();
    }

    private LocationDto mapLocationToDto(Location lc) {
        var dto = new LocationDto(lc.getId(), lc.getName(), lc.getType(), lc.getIsActive(), lc.getBinNumber());
        return dto;
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
                .map(data -> mapWarehouseToDto(data));
    }

    @Override
    public Page<RackDto> getRacks(WarehouseDto dto, ZoneDto zoneDto, Pageable pageable) {
        return null;
    }

}
