package id.putra.wms.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import id.putra.wms.entity.Inventory;
import id.putra.wms.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public void addInventory(Map<String, Object> body) {
        var inventory = new Inventory();
        inventory.setName((String) body.get("name"));
        inventory.setDate(new Date());
        inventory.setReceivedDate(new Date());
        inventoryRepository.save(inventory);
    }
}
