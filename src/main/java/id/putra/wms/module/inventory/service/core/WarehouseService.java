package id.putra.wms.module.inventory.service.core;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import id.putra.wms.service.CRUDService;
import id.putra.wms.service.PagingService;
import id.putra.wms.shared.base.dto.param.SearchParam;
import id.putra.wms.shared.exceptions.MasterDataException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseService implements CRUDService<WarehouseDto, MasterDataException>, PagingService<WarehouseDto> {

    private final WarehouseRepository warehouseRepository;
    private final ZoneRepository zoneRepository;
    private final RackRepository rackRepository;

    @Override
    public WarehouseDto getDataById(String id) {
        Optional<Warehouse> wh = warehouseRepository.findById(id);
        if (wh.isPresent()) {
            return mapWarehouseToDto(wh.get());
        }

        return null;
    }

    public ZoneDto getRackByZoneID(String id) {
        Optional<Zone> zn = zoneRepository.findById(id);
        if (zn.isPresent()) {
            return mapZoneToDto(zn.get());
        }

        return null;
    }

    public RackDto getLocationByRack(String id) {
        Optional<Rack> rk = rackRepository.findById(id);
        if (rk.isPresent()) {
            return mapRackToDto(rk.get());
        }

        return null;
    }

    @Override
    public void add(WarehouseDto dto) throws MasterDataException {

        if (warehouseRepository.existsById(dto.id())) {
            throw new MasterDataException("Warehouse %s already exist".formatted(dto.id()));
        }

        var warehouse = new Warehouse();
        warehouse.setId(dto.id());
        warehouse.setName(dto.name());

        List<Zone> zones = warehouse.getZones().stream().map(zoneReq -> {
            var zone = new Zone();
            zone.setId(zoneReq.getId());
            zone.setName(zoneReq.getName());
            zone.setType(zoneReq.getType());
            zone.setWarehouse(warehouse);

            return zone;

        }).toList();
        warehouse.setZones(zones);

        warehouseRepository.saveAndFlush(warehouse);
    }

    @Override
    public void update(WarehouseDto form) throws MasterDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) throws MasterDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<WarehouseDto> getAll(SearchParam param) {
        int page = param.getPage() != null ? param.getPage() - 1 : 0;
        int size = param.getSize() != null ? param.getSize() : 10;

        var newPageable = PageRequest.of(page, size,
                param.getSort() != null ? Sort.by(param.getSort().stream().map(s -> {
                    String field = "";
                    switch (s.getField()) {
                        case "received_date":
                            field = "receivedDate";
                            break;
                        default:
                            field = s.getField();
                            break;
                    }

                    if (s.getDir().equals("asc")) {

                        return Order.asc(field);
                    } else {
                        return Order.desc(field);
                    }
                }).toList()) : Sort.unsorted());
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
        return warehouseRepository.findAll(Example.of(entity, example), newPageable)
                .map(data -> mapWarehouseToDto(data));

    }

    private WarehouseDto mapWarehouseToDto(Warehouse wh) {
        var dto = new WarehouseDto(wh.getId(), wh.getName(), wh.getIsActive(), wh.getLocation(), wh.getArea(),
                wh.getTotal(), wh.getZones().stream().map(z -> mapZoneToDto(z)).toList());

        return dto;
    }

    private ZoneDto mapZoneToDto(Zone zn) {
        var dto = new ZoneDto(zn.getCode(), zn.getName(), zn.getIsActive(), zn.getType(),
                zn.getRacks().stream().map(r -> mapRackToDto(r)).toList());
        return dto;
    }

    private RackDto mapRackToDto(Rack rk) {
        var dto = new RackDto(rk.getId(), rk.getName(), rk.getRows(), rk.getCols(),
                rk.getLocations().stream().map(r -> mapLocationToDto(r)).toList());
        return dto;
    }

    private LocationDto mapLocationToDto(Location lc) {
        var dto = new LocationDto(lc.getId(), lc.getName(), lc.getType(), lc.getIsActive(), lc.getBinNumber());
        return dto;
    }

}
