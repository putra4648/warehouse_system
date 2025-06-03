package id.putra.wms.service;

import org.springframework.stereotype.Service;

import id.putra.wms.dto.InventoryDto;
import id.putra.wms.entity.Inventory;
import id.putra.wms.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public void addInventory(InventoryDto form) {
        var inventory = new Inventory();

        inventory.setName(form.getName());
        inventory.setDate(form.getDate());
        inventory.setSupplier(form.getSupplier());
        inventory.setReceivedDate(form.getReceived_date());
        inventory.setReference(form.getRef());
        inventory.setStatus(form.getStatus());

        inventoryRepository.save(inventory);
    }
}
