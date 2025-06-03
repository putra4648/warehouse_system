package id.putra.wms.repository;

import org.springframework.data.repository.CrudRepository;

import id.putra.wms.entity.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}
