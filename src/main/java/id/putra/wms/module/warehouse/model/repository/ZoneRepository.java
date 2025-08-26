package id.putra.wms.module.warehouse.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.warehouse.model.entity.Warehouse;
import id.putra.wms.module.warehouse.model.entity.Zone;

public interface ZoneRepository extends JpaRepository<Zone, String> {
    Page<Zone> findByWarehouse(Warehouse warehouse, Pageable pageable);

    Zone findByWarehouseAndById(Warehouse warehouse, String id);

}
