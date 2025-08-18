package id.putra.wms.module.inventory.service.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import id.putra.wms.module.inventory.dto.form.InventoryForm;
import id.putra.wms.module.inventory.model.entity.InventoryItem;
import id.putra.wms.module.inventory.model.repository.InventoryRepository;
import id.putra.wms.shared.base.dto.param.SearchParam;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public void addInventory(InventoryForm form) {
        var inventory = new InventoryItem();

        // inventory.setName(form.getName());
        // inventory.setDate(form.getDate());
        // inventory.setSupplier(form.getSupplier());
        // inventory.setReceivedDate(form.getReceived_date());
        // inventory.setReference(form.getReference());
        inventory.setStatus(form.getStatus());

        inventoryRepository.save(inventory);
    }

    public Map<String, Object> getAll(SearchParam param) {
        Pageable newPageable = PageRequest.of(param.getPage() - 1, param.getSize(),
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
        var entity = new InventoryItem();
        // entity.setSupplier(param.getFilter().isEmpty() ? "" : (String)
        // param.getFilter().get(0).getValue());
        ExampleMatcher example = ExampleMatcher.matching().withMatcher("supplier",
                (matcher) -> matcher.ignoreCase().startsWith());
        var page = inventoryRepository.findAll(Example.of(entity, example), newPageable);
        var result = page.getContent().stream().map(data -> {
            var dto = new InventoryForm();

            dto.setId(data.getId());
            // dto.setName(data.getName());
            // dto.setDate(data.getDate());
            // dto.setSupplier(data.getSupplier());
            // dto.setReceived_date(data.getReceivedDate());
            // dto.setReference(data.getReference());
            dto.setStatus(data.getStatus());

            return dto;
        }).toList();
        return new HashMap<String, Object>() {
            {
                put("lastPage", page.getTotalPages());
                put("data", result);
            }
        };
    }
}
