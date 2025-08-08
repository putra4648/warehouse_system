package id.putra.wms.module.warehouse.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.putra.wms.module.warehouse.model.entity.Rack;

public interface RackRepository extends JpaRepository<Rack, String> {

}
