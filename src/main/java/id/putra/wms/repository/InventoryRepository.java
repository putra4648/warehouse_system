package id.putra.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
