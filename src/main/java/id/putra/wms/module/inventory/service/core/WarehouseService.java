package id.putra.wms.module.inventory.service.core;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

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
import id.putra.wms.module.warehouse.service.adapter.command.WarehouseCommandAdapter;
import id.putra.wms.module.warehouse.service.adapter.query.WarehouseQueryAdapter;
import id.putra.wms.module.warehouse.service.core.WarehouseCoreService;
import id.putra.wms.service.CRUDService;
import id.putra.wms.service.PagingService;
import id.putra.wms.shared.base.dto.param.SearchParam;
import id.putra.wms.shared.exceptions.DataNotFoundException;
import id.putra.wms.shared.exceptions.MasterDataException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseService implements WarehouseCoreService {

    private final WarehouseQueryAdapter warehouseQueryAdapter;
    private final WarehouseCommandAdapter warehouseCommandAdapter;

    // @Override
    // public void save(WarehouseDto dto) {

    // if (warehouseRepository.existsById(dto.id())) {getById
    // throw new MasterDataException("Warehouse %s already
    // exist".formatted(dto.id()));
    // }

    // var warehouse = new Warehouse();
    // warehouse.setId(dto.id());
    // warehouse.setName(dto.name());

    // List<Zone> zones = warehouse.getZones().stream().map(zoneReq -> {
    // var zone = new Zone();
    // zone.setId(zoneReq.getId());
    // zone.setName(zoneReq.getName());
    // zone.setType(zoneReq.getType());
    // zone.setWarehouse(warehouse);

    // return zone;

    // }).toList();
    // warehouse.setZones(zones);

    // warehouseRepository.saveAndFlush(warehouse);
    // }

    @Override
    public Page<WarehouseDto> getWarehouses(WarehouseDto dto, Pageable pageable) {
        return warehouseQueryAdapter.getWarehouses(dto, pageable);
    }

    @Override
    public void save(WarehouseDto req) {
        warehouseCommandAdapter.save(req);
    }

    @Override
    public Page<RackDto> getRacks(WarehouseDto dto, ZoneDto zoneDto, Pageable pageable) {
        return warehouseQueryAdapter.getRacks(dto, zoneDto, pageable);
    }

    @Override
    public Page<ZoneDto> getZonesByWarehouseId(WarehouseDto dto, Pageable pageable) {
        return warehouseQueryAdapter.getZonesByWarehouseId(dto, pageable);
    }

    @Override
    public WarehouseDto getWarehouseById(WarehouseDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWarehouseById'");
    }

    @Override
    public ZoneDto getZoneByIdByWarehouseId(WarehouseDto dto, ZoneDto zoneDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getZoneByIdByWarehouseId'");
    }

    @Override
    public RackDto getLocationByRack(WarehouseDto dto, ZoneDto zoneDto, RackDto rackDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocationByRack'");
    }

}
