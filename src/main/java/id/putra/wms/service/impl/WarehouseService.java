package id.putra.wms.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import id.putra.wms.dto.WarehouseDto;
import id.putra.wms.dto.param.SearchParam;
import id.putra.wms.dto.response.PagingResponse;
import id.putra.wms.entity.Location;
import id.putra.wms.entity.Warehouse;
import id.putra.wms.exceptions.MasterDataException;
import id.putra.wms.repository.WarehouseRepository;
import id.putra.wms.service.CRUDService;
import id.putra.wms.service.PagingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseService implements CRUDService<WarehouseDto, MasterDataException>, PagingService<WarehouseDto> {

    private final WarehouseRepository repository;

    @Override
    public WarehouseDto getProductById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public void add(WarehouseDto dto) throws MasterDataException {
        if (repository.existsById(dto.getId())) {
            throw new MasterDataException("Warehouse %s already exist".formatted(dto.getId()));
        }

        var entity = new Warehouse();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        var location = new Location();
        location.setId(dto.getLocation().getId());
        location.setName(dto.getLocation().getName());
        location.setWarehouse(entity);

        entity.setLocation(location);

        repository.saveAndFlush(entity);
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
    public PagingResponse<WarehouseDto> getAll(SearchParam param) {
        var newPageable = PageRequest.of(param.getPage() - 1, param.getSize(),
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
        var page = repository.findAll(Example.of(entity, example), newPageable);
        var result = page.getContent().stream().map(data -> mapToDto(data)).toList();
        return new PagingResponse<>((long) page.getTotalPages(), result);
    }

    private WarehouseDto mapToDto(Warehouse wh) {
        var dto = new WarehouseDto();

        dto.setId(wh.getId());
        dto.setName(wh.getName());
        dto.setStatus(wh.getIsActive());

        return dto;
    }

}
