package id.putra.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.entity.InventoryItem;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
}
