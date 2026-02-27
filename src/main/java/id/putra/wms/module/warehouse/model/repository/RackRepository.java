package id.putra.wms.module.warehouse.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import id.putra.wms.module.warehouse.model.entity.Rack;

public interface RackRepository extends JpaRepository<Rack, Long>, JpaSpecificationExecutor<Rack> {

}
