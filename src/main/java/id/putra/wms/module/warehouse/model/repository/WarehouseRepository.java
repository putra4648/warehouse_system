package id.putra.wms.module.warehouse.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.warehouse.model.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, String> {

}
